package akka;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultStoreActor extends AbstractActor {
    final Map<String, Map<String, String>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                        FunctionResult.class,
                        unitApply -> {
                            if (!store.containsKey(unitApply.getPackageId())) {
                                store.put(unitApply.getPackageId(), new HashMap<>());
                            } else {
                                ArrayList<Test> 
                            }
                            store.get(unitApply.getPackageId()).put(unitApply.getTestName(), unitApply.getResult());
                        }
                )
                .match(MessageObject.class, m -> sender().tell(store.get(m.getPackageId()), self()))
                .build();
    }
}
