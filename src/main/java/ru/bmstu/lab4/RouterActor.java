package main.java.ru.bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.BalancingRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import main.java.ru.bmstu.lab4.Messages.Executed;
import main.java.ru.bmstu.lab4.Messages.GetRequest;
import main.java.ru.bmstu.lab4.Messages.PostRequest;
import main.java.ru.bmstu.lab4.Messages.Test;

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

        router = new Router(new BalancingRoutingLogic(), routees);
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
            router.route(new Executed(msg.getPackageID(), msg.getJsScript(), msg.getFunctionName(), test.getExpectedResult(), test.getParams()), storage);
        }
    }
}
