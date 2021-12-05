package main.java.ru.bmstu.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ExecuterActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match();
    }

    public void execute(PostRequest msg) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    }
}
