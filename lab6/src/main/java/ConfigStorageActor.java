import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import com.sun.net.httpserver.HttpServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConfigStorageActor extends AbstractActor {
    private List<String> listServers = new ArrayList<>();
    private final Random random = new Random();


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ServerHttp.MessageObject.class, messageObject -> {
                    sender().tell(getRandomServerPort(), ActorRef.noSender());
                })
                .match(ZooKeeperWatcher.MessageSendServersList.class, messageSendServersList -> {
                    listServers = messageSendServersList.getServers();
                })
                .build();
    }

    private String getRandomServerPort() {
        System.out.println(listServers);
        return listServers.get(random.nextInt(listServers.size()));
    }
}
