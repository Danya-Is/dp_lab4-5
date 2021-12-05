package main.java.ru.bmstu.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class StorageActor  extends AbstractActor {
    private final Map<Integer, String> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Result.class, msg -> );
    }

    private void addMessage
}
