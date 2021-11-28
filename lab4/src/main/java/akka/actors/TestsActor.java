package akka;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;

public class TestsActor extends AbstractActor {
    private final ActorSelection testPerformRouter = getContext().actorSelection("/messageStore");

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                MessageTests.class,
                msg -> {
                    System.out.println("receive tests: " + msg.getTests());
                    for (Test test : msg.getTests()) {
                        testPerformRouter.tell(new MessageTest(
                                msg.getPackageId(), msg.getScript(), msg.getFunctionTitle(), test), self()
                        );
                    }
                }
        ).build();
    }
}
