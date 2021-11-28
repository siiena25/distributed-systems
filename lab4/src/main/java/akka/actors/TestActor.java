package akka.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;
import akka.models.MessageStore;
import akka.models.MessageTest;
import akka.models.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class TestActor extends AbstractActor {
    private final static String SCRIPT_NAME = "nashorn";
    private final ActorSelection messageStore = getContext().actorSelection("/user/messageStoreActor");

    private Test createTest(String script, String functionTitle, String testName,
                            String expectedResult, ArrayList<Integer> params) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(SCRIPT_NAME);
        engine.eval(script);
        Invocable invocable = (Invocable) engine;
        String res = invocable.invokeFunction(functionTitle, params.toArray()).toString();
        Test test = new Test(testName, expectedResult, params);
        test.setResult(expectedResult.equals(res));
        return test;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                MessageTest.class,
                item -> {
                    ArrayList<Test> tests = new ArrayList<>();
                    tests.add(createTest(
                            item.getScript(),
                            item.getFunctionTitle(),
                            item.getTest().getTestName(),
                            item.getTest().getExpectedResult(),
                            item.getTest().getParams()
                    ));
                    System.out.println("msg store: " + messageStore);
                    messageStore.tell(new MessageStore(item.getPackageId(), tests), ActorRef.noSender());
                }
        ).build();
    }
}
