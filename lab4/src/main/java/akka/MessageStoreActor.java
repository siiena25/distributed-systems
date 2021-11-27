package akka;

import akka.actor.AbstractActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageStoreActor extends AbstractActor {
    final Map<String, ArrayList<Test>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                        MessageStore.class,
                        msg -> {
                            if (!store.containsKey(msg.getPackageId())) {
                                store.put(msg.getPackageId(), msg.getTests());
                            } else {
                                ArrayList<Test> tests = store.get(msg.getPackageId());
                                tests.addAll(msg.getTests());

                            }
                            store.get(msg.getPackageId()).put(msg.getTestName(), msg.getResult());
                        }
                )
                .match(MessageObject.class, m -> sender().tell(store.get(m.getPackageId()), self()))
                .build();
    }
}
