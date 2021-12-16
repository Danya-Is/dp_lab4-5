package ru.bmstu.lab5;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

public class ResponseTimeApp {
    public static final String STARTED = "Started";

    public static void main(String[] args) {
        System.out.println(STARTED);
        ActorSystem actorSystem = ActorSystem.create("routes");
        final Http http = Http.get(actorSystem);
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow

    }

    private static Flow<HttpRequest, HttpResponse, NotUsed> createFlow(ActorSystem actorSystem, ActorMaterializer actorMaterializer) {
        return Flow.of(HttpRequest.class)
                .map(request -> {
                    request.getUri().query();
                })
    }
}
