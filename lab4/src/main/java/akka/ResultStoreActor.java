package akka;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.Map;

public class ResultStoreActor extends AbstractActor {
    private Map<String, Map<String, String>> store = 
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                FunctionResult.class,
                unitApply -> {
                    if (unitApply)
                }
        )
    }
}
