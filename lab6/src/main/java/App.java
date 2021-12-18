import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class App {

    private static final int SESSION_TIMEOUT = 3000;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        if (args.length < 2) {
            System.out.println("Usage: App localhost:2181 8000 8001");
            System.exit(-1);
        }
        String zooKeeperAddress = args[0];

        BasicConfigurator.configure();
        System.out.println("Start... " + Arrays.toString(args));
        ActorSystem system = ActorSystem.create("lab6");
        ActorRef configStorageActor = system.actorOf(Props.create(ConfigStorageActor.class));
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(system);
        final Http http = Http.get(system);

        ZooKeeper zooKeeper = new ZooKeeper(zooKeeperAddress, SESSION_TIMEOUT, null);
        new ZooKeeperWatcher(zooKeeper, configStorageActor);

        List<CompletionStage<ServerBinding>> bindings = new ArrayList<>();
        StringBuilder serversInfo = new StringBuilder("Servers online at\n");
        for (int i = 1; i < args.length; i++) {
            ServerHttp httpServer = new ServerHttp(http, configStorageActor, zooKeeper, args[i]);
            final Flow<HttpRequest, HttpResponse, ?> routeFlow = httpServer.createRoute().flow(system, actorMaterializer);
            bindings.add(http.bindAndHandle(
                    routeFlow,
                    ConnectHttp.toHost(HOST, Integer.parseInt(args[i])),
                    actorMaterializer
            ));
            serversInfo.append("http://localhost:").append(args[i]).append("/\n");
        }

        if (bindings.size() == 0) {
            System.err.println("No servers are running!");
        }
        System.out.println(serversInfo + "\nPress RETURN to stop...");
        System.in.read();
        for (CompletionStage<ServerBinding> bindingCompletionStage : bindings) {
            bindingCompletionStage
                    .thenCompose(ServerBinding::unbind)
                    .thenAccept(unbind -> system.terminate());
        }
    }
}
