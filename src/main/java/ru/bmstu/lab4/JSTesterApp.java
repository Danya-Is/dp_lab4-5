package main.java.ru.bmstu.lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class JSTesterApp {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("lab4");
        ActorRef storeActor = actorSystem.actorOf(Props.create())
    }
}
