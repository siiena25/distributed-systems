import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

public class ZooKeeperWatcher implements Watcher {
    private final ZooKeeper zooKeeper;
    private final ActorRef actorConf;

    public ZooKeeper(ZooKeeper zooKeeper, ActorRef actorConf) {
        this.zooKeeper = zooKeeper;
        this.actorConf = actorConf;
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            zooKeeper.getChildren("/servers", this);

        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendServers() {
        List<String> servers = new ArrayList<>();
        try {
            for (String s : zooKeeper.getChildren("/servers", this)) {
                servers.add(new String(zooKeeper.getData("/servers/" + s, false, null)));
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        actorConf.tell(servers, ActorRef.noSender());
    }
}
