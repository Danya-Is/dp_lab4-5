package ru.bmstu.lab5;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.japi.Pair;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

public class ResponseTimeApp {
    public static final String STARTED = "Started";
    public static final String TEST_URL = "testUrl";
    public static final String LOCALHOST = "localhost";
    public static final String COUNT = "count";
    public static final String DEFAULT_COUNT = "1";

    public static void main(String[] args) {
        System.out.println(STARTED);
        ActorSystem actorSystem = ActorSystem.create("routes");
        final Http http = Http.get(actorSystem);
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = createFlow(actorSystem, actorMaterializer);

    }

    private static Flow<HttpRequest, HttpResponse, NotUsed> createFlow(ActorSystem actorSystem, ActorMaterializer actorMaterializer) {
        return Flow.of(HttpRequest.class)
                .map(request -> {
                    Query query = request.getUri().query();
                    String url = query.get(TEST_URL).orElse(LOCALHOST);
                    int count = Integer.parseInt(query.get(COUNT).orElse(DEFAULT_COUNT));
                    return new Pair<String, Integer>(url, count);
                })
                .mapAsync(4, pair -> {
                    Patterns.ask()
                })
    }
}
