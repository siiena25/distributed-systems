package akka;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageStoreActor extends AbstractActor {
    final Map<Integer, ArrayList<Test>> messageStore = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                        MessageStore.class,
                        msg -> {
                            if (!messageStore.containsKey(msg.getPackageId())) {
                                messageStore.put(msg.getPackageId(), msg.getTests());
                            } else {
                                ArrayList<Test> tests = messageStore.get(msg.getPackageId());
                                tests.addAll(msg.getTests());
                                messageStore.replace(msg.getPackageId(), tests);
                            }
                            System.out.println("message store: " + messageStore);
                        }
                )
                .match(MessageObject.class, s ->
                        sender().tell(new MessageStore(s.getPackageId(), messageStore.get(s.getPackageId())), self())
                ).build();
    }
}
