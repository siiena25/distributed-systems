package akka;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class ResultStoreActor extends AbstractActor {
    private Map<String, Map<String, String>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                FunctionResult.class,
                unitApply -> {
                    if (!store.containsKey(unitApply.getPackageId())) {
                        store.put(unitApply.getPackageId(), new HashMap<>());
                    }
                    store.get(unitApply.getPackageId()).put(unitApply.getTitle(), unitApply.getResult());
                }
        ).match(String.class, id -> sender().tell(printId(id), self()))
    }

    private OutputResult printId(String id) {
        return new OutputResult(id, store.get(id));
    }
}
