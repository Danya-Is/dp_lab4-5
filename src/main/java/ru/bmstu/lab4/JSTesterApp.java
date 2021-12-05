package main.java.ru.bmstu.lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.util.Timeout;
import main.java.ru.bmstu.lab4.Messages.GetRequest;
import scala.concurrent.Future;

import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

public class JSTesterApp {

    private final ActorRef router;

    public JSTesterApp(ActorRef router) {
        this.router = router;
    }

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("lab4");
        final Http http = Http.get(actorSystem);
        final ActorMaterializer materializer = ActorMaterializer.create(actorSystem);

    }

    private Route createRoute() {
        return JSTesterApp(
                get(() -> parameter("packageID", (id) -> {
                    Future<Object> res = Patterns.ask(router, new GetRequest(Integer.parseInt(id)), Timeout.create(Duration.ofSeconds(5)));
                    
                })),
                post(() -> {

                }))
    }
}
