package main.java.ru.bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import main.java.ru.bmstu.lab4.Messages.GetRequest;
import main.java.ru.bmstu.lab4.Messages.Response;
import main.java.ru.bmstu.lab4.Messages.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StorageActor  extends AbstractActor {
    private final Map<String, ArrayList<String>> storage = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Result.class, this::storeMessage)
                .match(GetRequest.class, msg -> sender().tell(new Response(msg.getPackageID(), storage.get(msg.getPackageID())), ActorRef.noSender()))
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
