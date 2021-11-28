package akka;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;

public class TestsActor extends AbstractActor {
    private final ActorSelection testPerformRouter = getContext().actorSelection("/messageStore");

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                MessageTests.class,
                msg -> {
                    for (Test test : msg.getTests()) {
                        testPerformRouter.tell(new MessageTest(
                                msg.getPackageId(), msg.getScript(), msg.getFunctionTitle(), test), self()
                        );
                    }
                }
        ).build();
    }
}
