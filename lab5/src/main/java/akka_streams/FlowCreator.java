package akka_streams;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.japi.Pair;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;

public class FlowCreator {
    private final ActorRef cacheActor;
    private final Materializer materializer;

    public FlowCreator(ActorRef cacheActor, Materializer materializer) {
        this.cacheActor = cacheActor;
        this.materializer = materializer;
    }

    Flow<HttpRequest, HttpResponse, NotUsed> create() {
        Flow.of(HttpRequest.class).map(param -> {
            Query query = param.getUri().query();
            String url = query.getOrElse("testUrl", "");
            Integer count = query.get("count").map(Integer::parseInt).orElse(1);
            return new Pair<>(url, count);
        }).mapAsync(10, request -> {
            TestResultParams msg = new TestResultParams(request.first(), request.second());
            
        })
    }
}