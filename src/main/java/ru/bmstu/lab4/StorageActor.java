package main.java.ru.bmstu.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StorageActor  extends AbstractActor {
    private final Map<Integer, ArrayList<String>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Result.class, this::addMessage)
                .build();
    }

    private void addMessage(Result msg) {
        ArrayList<String>
        store.put(msg.getPackageID(), msg.getValue());
    }
}
