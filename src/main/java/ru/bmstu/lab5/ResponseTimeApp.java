package ru.bmstu.lab5;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.japi.Pair;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import org.asynchttpclient.AsyncHttpClient;
import static org.asynchttpclient.Dsl.asyncHttpClient;


public class ResponseTimeApp {
    public static final String STARTED = "Started";
    public static final String TEST_URL = "testUrl";
    public static final String LOCALHOST = "localhost";
    public static final String COUNT = "count";
    public static final String DEFAULT_COUNT = "1";
    public static final String CASHER = "casher";
    public static final int PORT = 8888;

    public static void main(String[] args) throws IOException {
        System.out.println(STARTED);
        ActorSystem actorSystem = ActorSystem.create("routes");
        ActorRef casher = actorSystem.actorOf(Props.create(CashActor.class), CASHER);
        final Http http = Http.get(actorSystem);
        final ActorMaterializer actorMaterializer = ActorMaterializer.create(actorSystem);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = createFlow(casher, actorMaterializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(LOCALHOST, PORT),
                actorMaterializer
        );
        System.out.println("Listening...  " + PORT);
        System.in.read();

        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> actorSystem.terminate());
    }

    private static Flow<HttpRequest, HttpResponse, NotUsed> createFlow(ActorRef casher, ActorMaterializer actorMaterializer) {
        return Flow.of(HttpRequest.class)
                .map(request -> {
                    Query query = request.getUri().query();
                    String url = query.get(TEST_URL).orElse(LOCALHOST);
                    int count = Integer.parseInt(query.get(COUNT).orElse(DEFAULT_COUNT));
                    return new Pair<>(url, count);
                })
                .mapAsync(4, (pair -> Patterns.ask(casher, pair.first(), Duration.ofSeconds(5)).thenCompose(time -> {
                    if ((float) time >= 0) {
                        return CompletableFuture.completedFuture(new Pair<>(pair.first(), (float)time));
                    }
                    return Source.from(Collections.singletonList(pair))
                            .toMat(createSink(), Keep.right())
                            .run(actorMaterializer)
                            .thenApply(t -> new Pair<>(pair.first(), (float)time/pair.second()));
                })))
                .map(result -> {
                    casher.tell(new Response(result.first(), result.second()), ActorRef.noSender());
                    return HttpResponse.create().withEntity(result.first() + ":" + result.second() + "\n");
                });
    }

    private static Sink<Pair<String, Integer>, CompletionStage<Long>> createSink(int copiesAmount) {
        return Flow.<Pair<String, Integer>>create()
                .mapConcat(pair -> Collections.nCopies(pair.second(), pair.first()))
                .mapAsync(, url -> {
                    AsyncHttpClient client = asyncHttpClient();
                    long startTime = System.currentTimeMillis();
                    client.prepareGet(url).execute();
                    long executeTime = System.currentTimeMillis() - startTime;
                    return CompletableFuture.completedFuture(executeTime);
                })
                .toMat(Sink.fold(0L, Long::sum), Keep.right());
    }
}
