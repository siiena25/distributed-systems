package akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestActor extends AbstractActor {
    private final static String OK_RESULT = "OK";
    private final static String FAIL_RESULT = "FAIL";
    private final static String SCRIPT_NAME = "nashorn";
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                UnitTest.class,
                item -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName(SCRIPT_NAME);
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
