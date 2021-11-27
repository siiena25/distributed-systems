package akka;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class TestActor extends AbstractActor {
    private final static String OK_RESULT = "OK";
    private final static String FAIL_RESULT = "FAIL";
    private final static String SCRIPT_NAME = "nashorn";

    private ArrayList<Test> createTests(String script, String functionTitle, String testName,
                                        String result, ArrayList<String> params) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(SCRIPT_NAME);
        engine.eval(script);
        Invocable invocable = (Invocable) engine;
        String res = invocable.invokeFunction(functionTitle, params.toArray()).toString();
        Test test = new Test(testName, result, params);
        test.setResult(result.equals(res));
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                UnitTest.class,
                item -> {
                    String answer = (result.equals(item.getResult())) ? OK_RESULT : FAIL_RESULT;
                    System.out.println("Expected result: " + item.getResult());
                    System.out.println("Received result: " + result);
                    System.out.println("Answer: " + answer);
                    sender().tell(new MessageStore(
                            item.getPackageId(),
                            createTests(

                            )
                    ), self());
                }
        ).build();
    }
}
