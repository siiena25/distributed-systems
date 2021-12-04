package akka_streams;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.japi.Pair;
import akka.pattern.Patterns;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Sink;
import org.asynchttpclient.AsyncHttpClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.asynchttpclient.Dsl.asyncHttpClient;

public class FlowCreator {
    private final ActorRef cacheActor;
    private final Materializer materializer;

    public FlowCreator(ActorRef cacheActor, Materializer materializer) {
        this.cacheActor = cacheActor;
        this.materializer = materializer;
    }

    Flow<HttpRequest, HttpResponse, NotUsed> createFlow() {
        Flow.of(HttpRequest.class).map(param -> {
            Query query = param.getUri().query();
            String url = query.getOrElse("testUrl", "");
            Integer count = query.get("count").map(Integer::parseInt).orElse(1);
            return new Pair<>(url, count);
        }).mapAsync(10, request -> {
            TestResultParams msg = new TestResultParams(request.first(), request.second());
            return Patterns.ask(cacheActor, msg, Duration.ofSeconds(5)).thenCompose(response -> {
                Optional<TestResult> res = (Optional<TestResult>) response;
                if (res.isPresent()) {
                    TestResult result = res.get();
                    float time = result.getAverageRequestTime();
                    System.out.println("Average time: " + time);
                    return CompletableFuture.completedFuture(time);
                }
                Sink<Pair<String, Integer>, CompletionStage<Float>> sink = createSink();
            })
        })
    }

    private Sink<Pair<String, Integer>, CompletionStage<Float>> createSink() {
        return Flow.<Pair<String, Integer>>create().mapConcat( param ->
                IntStream.range(0, param.second()).mapToObj(i -> param).collect(Collectors.toCollection(ArrayList::new))
        ).mapAsync(10, param -> {
            AsyncHttpClient httpClient = asyncHttpClient();
            long start = System.currentTimeMillis();
            return client.pre
        })
    }
}
