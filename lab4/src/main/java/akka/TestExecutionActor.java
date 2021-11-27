package akka;

import akka.actor.AbstractActor;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestExecutionActor extends AbstractActor {
    private final static String OK_RESULT = "OK";
    private final static String FAIL_RESULT = "FAIL";
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                UnitTest.class,
                item -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
                    engine.eval(item.getScript());
                    Invocable invocable = (Invocable) engine;
                    String result = invocable.invokeFunction(
                            item.getFunctionTitle(),
                            item.getParams().toArray()
                    ).toString();
                    String answer = (result.equals(item.getResult())) ? OK_RESULT : FAIL_RESULT;
                    System.out.println("Expected result: " + item.getResult());
                    System.out.println("Received result: " + result);
                    System.out.println("Answer: " + answer);
                    sender().tell(new FunctionResult(
                            item.getPackageId(),
                            item.getTestName(),
                            answer
                    ), self());
                }
        ).build();
    }
}
