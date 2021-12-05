package main.java.ru.bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class RouterActor extends AbstractActor {

    private final ActorRef storage;

    public RouterActor() {
        storage = getContext().actorOf(new Props(StorageActor.class));
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(PostRequest.class, );
    }

    private void executeTests(PostRequest msg) {
        for (Test test : msg.getTests()) {
            sender().tell(new Executed(msg.getPackageID(), msg.getJsScript(), msg.getFunctionName(), test.getExpectedResult(), test.getParams()), self());
        }
    }
}
