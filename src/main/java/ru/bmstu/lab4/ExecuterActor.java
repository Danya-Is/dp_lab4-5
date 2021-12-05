package main.java.ru.bmstu.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import main.java.ru.bmstu.lab4.Messages.Executed;
import main.java.ru.bmstu.lab4.Messages.Result;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExecuterActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Executed.class, msg -> sender().tell(new Result(msg.getPackageID(), execute(msg)), self()))
                .build();
    }

    public String execute(Executed executed) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(executed.getJsScript());
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(executed.getFunctionName(), executed.getParams()).toString();
    }
}
