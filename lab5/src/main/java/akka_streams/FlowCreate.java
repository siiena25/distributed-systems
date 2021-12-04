package akka_streams;

import akka.actor.ActorRef;
import akka.stream.Materializer;

public class FlowCreate {
    private final ActorRef cacheActor;
    private final Materializer materializer;

    public FlowCreate(ActorRef cacheActor, Materializer materializer) {
        this.cacheActor = cacheActor;
        this.materializer = materializer;
    }

    
}
