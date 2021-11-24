package akka;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestExecutionActor extends AbstractActor {
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
                    String answer = (result.equals(item.getResult())) ? "success" : "fail";
                    System.out.println("Expected result: " + item.getResult());
                    System.out.println("Received result: " + result);
                    System.out.println("Answer: " + answer);
                    sender().tell(new FunctionResult(
                            item.getPackageId(),
                            item.getTestTitle(),
                            answer
                    ), self());
                }
        ).build();
    }
}
