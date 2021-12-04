package akka_streams;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
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
                }
        )
    }
}
