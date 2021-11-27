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
import akka.pattern.PatternsCS;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import scala.Int;
import scala.concurrent.Future;

import java.util.ArrayList;

import static akka.http.javadsl.server.Directives.*;

public class App {
    private final static int SERVER_PORT = 8080;
    private final static String SERVER_HOST = "localhost";
    private final static int NR_VALUE = 5;
    private final static String QUERY_NAME = "packageId";

    public static Route createRoute(ActorRef resultStoreActor, ActorRef testExecutionActor) {
        return route(
                get(() -> parameter(QUERY_NAME, packageId -> {
                    Future<Object> res = PatternsCS.ask(resultStoreActor, Integer.parseInt(packageId), 5000);
                    return completeOKWithFuture(res, Jackson.marshaller());
                })),
                post(() -> entity(Jackson.unmarshaller(ResultStoreFunction.class), message -> {
                    ArrayList<UnitTest> tests = UnitTest.funcHandler(message);
                    System.out.println("tests: " + tests);
                    for (UnitTest test : tests) {
                        testExecutionActor.tell(test, resultStoreActor);
                    }
                    return complete("OK");
                }))

        );
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();
        ActorRef resultStoreActor = system.actorOf(Props.create(ResultStoreActor.class));
        ActorRef testExecutionActor = system.actorOf(new RoundRobinPool(NR_VALUE).props(Props.create(TestExecutionActor.class)));
        final Http http = Http.get(system);
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, ?> handler = createRoute(resultStoreActor, testExecutionActor).flow(system, actorMaterializer);
        final ConnectHttp connect = ConnectHttp.toHost(SERVER_HOST, SERVER_PORT);
        http.bindAndHandle(handler, connect, actorMaterializer);
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
