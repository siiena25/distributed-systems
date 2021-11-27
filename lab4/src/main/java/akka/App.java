package akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;

import java.util.ArrayList;

import static akka.http.javadsl.server.Directives.*;

public class App {
    public static Route createRoute(ActorRef resultStoreActor, ActorRef testExecutionActor) {
        return route(
                get(() -> parameter("packageId", key -> {
                    Future<Object> res = Patterns.ask(resultStoreActor, key, 5000);
                    return completeOKWithFuture(res, Jackson.marshaller());
                })),
                post(() -> entity(Jackson.unmarshaller(ResultStoreFunction.class), message -> {
                    ArrayList<UnitTest> tests = UnitTest.funcHandler(message);
                    for (UnitTest test : tests) {
                        testExecutionActor.tell(test, resultStoreActor);
                    }
                    return complete("Success");
                }))

        );
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();
        ActorRef resultStoreActor = system.actorOf(Props.create(ResultStoreActor.class));
        ActorRef testExecutionActor = system.actorOf(new RoundRobinPool(5).props(Props.create(TestExecutionActor.class)));
        Http http = Http.get(system);
        ActorMaterializer actorMaterializer = ActorMaterializer.create(system);
        Flow<HttpRequest, HttpResponse, ?> handler = createRoute(resultStoreActor, testExecutionActor).flow(system, actorMaterializer);
        ConnectHttp connect = ConnectHttp.toHost("localhost", 8080);
        http.bindAndHandle(handler, connect, actorMaterializer);
    }
}
