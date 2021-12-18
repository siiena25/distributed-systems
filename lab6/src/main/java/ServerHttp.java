import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import org.apache.zookeeper.*;

import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

public class ServerHttp implements Watcher {
    private static final String HOST = "localhost:";
    private final Http http;
    private final ActorRef actorConf;
    private final ZooKeeper zooKeeper;
    private final String path;
    private final String URL_PARAMETER = "url";
    private final String COUNT_PARAMETER = "count";
    private final String ZERO_COUNTER = "0";
    private final String PATTERN_URL = "http://%s/?url=%s&count=%d";

    public ServerHttp(Http http, ActorRef actorConf, ZooKeeper zooKeeper, String port) throws InterruptedException, KeeperException {
        this.http = http;
        this.actorConf = actorConf;
        this.zooKeeper = zooKeeper;
        this.path = HOST + port;
        zooKeeper.create(
                "/servers/" + path,
                path.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL
        );
    }

    public Route createRoute() {
        return route(path("", () ->
                        route(get(() ->
                                        parameter(URL_PARAMETER, (url) ->
                                                parameter(COUNT_PARAMETER, (count) -> {
                                                    if (count.equals(ZERO_COUNTER)) {
                                                        return completeWithFuture(
                                                                http.singleRequest(HttpRequest.create(url))
                                                        );
                                                    }
                                                    return completeWithFuture(Patterns
                                                            .ask(actorConf, new MessageGetRandomServer(), Duration.ofMillis(5000))
                                                            .thenCompose(port ->
                                                                    http.singleRequest(HttpRequest.create(
                                                                            String.format(
                                                                                    PATTERN_URL,
                                                                                    port,
                                                                                    url,
                                                                                    Integer.parseInt(count) - 1
                                                                            )
                                                                    )))
                                                    );
                                                })
                                        )
                                )
                        )
                )
        );
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            zooKeeper.getData(path, this, null);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MessageGetRandomServer {
        public MessageGetRandomServer() {}
    }
}
