package main.java.ru.bmstu.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class RouterActor extends AbstractActor {
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
