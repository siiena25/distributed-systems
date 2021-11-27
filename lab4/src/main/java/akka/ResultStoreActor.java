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
                        msg -> {
                            if (!store.containsKey(msg.getPackageId())) {
                                store.put(msg.getPackageId(), new HashMap<>());
                            } else {
                                ArrayList<Test> tests = store.get(msg.getPackageId());
                                tests.addAll()
                            }
                            store.get(msg.getPackageId()).put(msg.getTestName(), msg.getResult());
                        }
                )
                .match(MessageObject.class, m -> sender().tell(store.get(m.getPackageId()), self()))
                .build();
    }
}
