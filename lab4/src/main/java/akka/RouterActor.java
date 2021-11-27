package akka;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;

public class RouterActor extends AbstractActor {
    private ActorSelection testPerformer = getContext().actorSelection("/testPerformer");

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                ResultStoreFunction.class,
                msg -> {
                    for (Test test : msg.getTests()) {
                        testPerformer.
                    }
                }
        );
    }
}
