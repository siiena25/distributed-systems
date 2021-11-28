package akka;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actors.MessageStoreActor;
import akka.actors.TestActor;
import akka.actors.TestsActor;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.models.MessageObject;
import akka.models.MessageTests;
import akka.pattern.Patterns;
import akka.pattern.PatternsCS;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class App {
    private final static int SERVER_PORT = 8080;
    private final static String SERVER_HOST = "localhost";
    private final static int NR_VALUE = 5;
    private final static String QUERY_NAME = "packageId";
    private final static int TIMEOUT_MILLIS = 5000;

    public Route createRoute(ActorRef messageStoreActor, ActorRef testsActor) {
        return route(
                get(() -> parameter(QUERY_NAME, packageId -> {
                    Future<Object> res = Patterns.ask(messageStoreActor, new MessageObject(Integer.parseInt(packageId)), TIMEOUT_MILLIS);
                    return completeOKWithFuture(res, Jackson.marshaller());
                })),
                post(() -> entity(Jackson.unmarshaller(MessageTests.class), message -> {
                    testsActor.tell(message, ActorRef.noSender());
                    return complete("Test successfully send");
                }))

        );
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("lab4");
        final Http http = Http.get(system);
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(system);
        final App app = new App();
        final ActorRef messageStoreActor = system.actorOf(Props.create(MessageStoreActor.class), "messageStoreActor");
        final ActorRef testsActor = system.actorOf(Props.create(TestsActor.class), "testsActor");
        final ActorRef testActor = system.actorOf(new RoundRobinPool(NR_VALUE).props(Props.create(TestActor.class)), "testActor");
        final Flow<HttpRequest, HttpResponse, ?> routeFlow = app.createRoute(messageStoreActor, testsActor).flow(system, actorMaterializer);
        final ConnectHttp connect = ConnectHttp.toHost(SERVER_HOST, SERVER_PORT);
        http.bindAndHandle(routeFlow, connect, actorMaterializer);
    }
}