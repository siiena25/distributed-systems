package akka;

import akka.actor.AbstractActor;

public class RouterActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                ResultStoreFunction.class,
                msg -> {
                    for (Test test : msg.getTests())
                }
        );
    }
}
