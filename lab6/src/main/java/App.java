import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;
import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: App localhost:2181 8000 8001");
            System.exit(-1);
        }

        BasicConfigurator.configure();
        System.out.println("Start... " + Arrays.toString(args));
        ActorSystem system = ActorSystem.create("lab6");
        ActorRef configStorageActor = system.actorOf(Props.create(ConfigStorageActor.class));
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(system);
        final Http http = Http.get(system);

        ZooKeeper zooKeeper = null;
        zooKeeper = new ZooKeeper(args[0], 3000, null);
        new ZooKeeperWatcher(zooKeeper, configStorageActor);

        List<CSta>
    }
}
