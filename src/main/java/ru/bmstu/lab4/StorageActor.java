package main.java.ru.bmstu.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.Map;

public class StorageActor  extends AbstractActor {
    private Map<Integer, String> store;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Result.class, );
    }
}
