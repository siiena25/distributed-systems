package akka_streams;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.Pair;

import java.util.HashMap;
import java.util.Optional;

public class CacheActor extends AbstractActor {
    private final HashMap<Pair<String, Integer>, TestResult> cache = new HashMap<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                TestResult.class,
                testResult -> cache.put(new Pair<>(TestResult.get, TestResult.getRequestsCount()), testResult)
        ).match(
                TestResultParams.class,
                testResultParams -> {
                    Pair<String, Integer> key = new Pair<>(testResultParams.getUrl(), testResultParams.getCount());
                    Optional<TestResult> testResult = Optional.ofNullable(cache.get(key));
                    sender().tell(testResult, ActorRef.noSender());
                }
        ).build();
    }
}
