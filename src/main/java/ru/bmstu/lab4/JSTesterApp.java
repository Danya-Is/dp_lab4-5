package main.java.ru.bmstu.lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.util.Timeout;
import main.java.ru.bmstu.lab4.Messages.GetRequest;
import main.java.ru.bmstu.lab4.Messages.PostRequest;
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
        JSTesterApp instance = new JSTesterApp(actorSystem.actorOf(Props.create(RouterActor.class)));
        Flow<HttpRequest, HttpResponse, >instance.createRoute().flow(actorSystem, materializer);

    }

    private Route createRoute() {
        return route(
                get(() -> parameter("packageID", (id) -> {
                    Future<Object> res = Patterns.ask(router, new GetRequest(Integer.parseInt(id)), Timeout.create(Duration.ofSeconds(5)));
                    return completeOKWithFuture(res, Jackson.marshaller());
                })),
                post(() -> entity(Jackson.unmarshaller(PostRequest.class), msg -> {
                    router.tell(msg, ActorRef.noSender());
                    return complete("OK");
                })));
    }
}
