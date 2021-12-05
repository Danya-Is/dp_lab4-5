package main.java.ru.bmstu.lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;

public class JSTesterApp {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("lab4");
        final Http http = Http.get(actorSystem);
        final ActorMaterializer materializer = ActorMaterializer.create(actorSystem);

    }

    private Route createRoute() {
        return new
    }
}
