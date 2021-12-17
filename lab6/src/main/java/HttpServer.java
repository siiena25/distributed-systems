import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import org.apache.zookeeper.*;

import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

public class HttpServer implements Watcher {
    private final Http http;
    private final ActorRef actorConf;
    private final ZooKeeper zooKeeper;
    private final String path;

    public HttpServer(Http http, ActorRef actorConf, ZooKeeper zooKeeper, String port) throws InterruptedException, KeeperException {
        this.http = http;
        this.actorConf = actorConf;
        this.zooKeeper = zooKeeper;
        this.path = "localhost:" + port;
        zooKeeper.create(
                "/servers/" + path,
                path.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL
        );
    }

    public Route createRoute() {
        return route(
                path("", () ->
                        route(
                                get(() ->
                                        parameter("url", (url) ->
                                                parameter("count", (count) -> {
                                                    if (count.equals("0")) {
                                                        return completeWithFuture(http.singleRequest(HttpRequest.create(url)));
                                                    }
                                                    return completeWithFuture(Patterns.ask(
                                                            actorConf, new MessageObject(), Duration.ofMillis(5000))
                                                    ))

                                                })))));
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
}
