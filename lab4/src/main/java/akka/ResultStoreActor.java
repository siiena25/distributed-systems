package akka;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class ResultStoreActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                FunctionResult.class,
                unitApply -> {
                    
                }
        )
    }
}
