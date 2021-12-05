package main.java.ru.bmstu.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StorageActor  extends AbstractActor {
    private final Map<Integer, ArrayList<String>> storage = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Result.class, this::storeMessage)
                .build();
    }

    private void storeMessage(Result msg) {
        ArrayList<String> results = storage.get(msg.getPackageID());
        if (results == null) {
            results = new ArrayList<>();
        }
        results.add(msg.getValue());
        storage.put(msg.getPackageID(), results);
    }
}
