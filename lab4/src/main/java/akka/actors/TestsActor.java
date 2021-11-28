package akka.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;
import akka.models.MessageTest;
import akka.models.MessageTests;
import akka.models.Test;

public class TestsActor extends AbstractActor {
    private final ActorSelection testPerformRouter = getContext().actorSelection("/user/testActor");

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
