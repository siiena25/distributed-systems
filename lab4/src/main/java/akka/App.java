package akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.pattern.PatternsCS;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class App {
    private final static int SERVER_PORT = 8080;
    private final static String SERVER_HOST = "localhost";
    private final static int NR_VALUE = 5;
    private final static String QUERY_NAME = "packageId";
    private final static int TIMEOUT_MILLIS = 5000;

    public static Route createRoute(ActorRef messageStoreActor, ActorRef testsActor) {
        return route(
                get(() -> parameter(QUERY_NAME, packageId -> {
                    CompletionStage<Object> res = PatternsCS.ask(messageStoreActor, new MessageObject(Integer.parseInt(packageId)), TIMEOUT_MILLIS);
                    return completeOKWithFuture(res, Jackson.marshaller());
                })),
                post(() -> entity(Jackson.unmarshaller(MessageTests.class), message -> {
                    testsActor.tell(message, ActorRef.noSender());
                    return complete("OK");
                }))

        );
    }

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create();
        ActorRef messageStoreActor = system.actorOf(Props.create(MessageStoreActor.class));
        ActorRef testsActor = system.actorOf(Props.create(TestsActor.class));
        ActorRef testActor = system.actorOf(new RoundRobinPool(NR_VALUE).props(Props.create(TestActor.class)));
        final Http http = Http.get(system);
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, ?> handler = createRoute(messageStoreActor, testsActor).flow(system, actorMaterializer);
        final ConnectHttp connect = ConnectHttp.toHost(SERVER_HOST, SERVER_PORT);
        final CompletionStage<ServerBinding> serverBinding = http.bindAndHandle(handler, connect, actorMaterializer);
        System.out.println("Start...");
        System.in.read();
        serverBinding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
    }
}

/*
{
"packageId":"11",
"jsScript":"var divideFn = function(a,b) { return a/b} ",
"functionName":"divideFn",
"tests": [
{"testName":"test1",
"expectedResult":"2.0",
"params":[2,1]
},
{"testName":"test2",
"expectedResult":"2.0",
"params":[4,2]
}
]
}
 */
