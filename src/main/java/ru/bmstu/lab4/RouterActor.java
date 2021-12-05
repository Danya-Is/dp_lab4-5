package main.java.ru.bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;

public class RouterActor extends AbstractActor {

    private final ActorRef storage;
    private final Router router;

    public RouterActor() {
        storage = getContext().actorOf(Props.create(StorageActor.class));
        getContext().watch(storage);

        ArrayList<Routee> routees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ActorRef executor = getContext().actorOf(Props.create(ExecuterActor.class));
            getContext().watch(executor);
            routees.add(new ActorRefRoutee(executor));
        }

        router = 
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(PostRequest.class, this::executeTests)
                .match(GetRequest.class, msg -> storage.tell(msg, sender()))
                .build();
    }

    private void executeTests(PostRequest msg) {
        for (Test test : msg.getTests()) {
            sender().tell(new Executed(msg.getPackageID(), msg.getJsScript(), msg.getFunctionName(), test.getExpectedResult(), test.getParams()), self());
        }
    }
}
