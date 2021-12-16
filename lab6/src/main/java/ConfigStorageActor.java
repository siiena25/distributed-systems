import akka.actor.AbstractActor;
import com.sun.net.httpserver.HttpServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConfigStorageActor extends AbstractActor {
    private List<String> listServers = new ArrayList<>();
    private final Random random = new Random();


    @Override
    public Receive createReceive() {
        return receiveBuilder().match(HttpServer.);
    }
}
