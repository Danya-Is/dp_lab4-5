package main.java.ru.bmstu.lab5;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class CashActor extends AbstractActor {

    private HashMap<String, Float> cash = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, msg -> sender().tell(cash.getOrDefault(msg, -1f), ActorRef.noSender()))
                .match(Response.class, msg -> cash.put(msg.getUrl(), msg.getAverageResponseTime()))
                .build();
    }
}
