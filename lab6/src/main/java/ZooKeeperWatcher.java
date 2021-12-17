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

    public ZooKeeperWatcher(ZooKeeper zooKeeper, ActorRef actorConf) {
        this.zooKeeper = zooKeeper;
        this.actorConf = actorConf;
        try {
            byte[] data = this.zooKeeper.getData("/servers", true, null);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        sendServers();
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
        actorConf.tell(new MessageSendServersList(servers), ActorRef.noSender());
    }

    static class MessageSendServersList {
        private final List<String> servers;

        public MessageSendServersList(List<String> servers) {
            this.servers = servers;
        }

        public List<String> getServers() {
            return servers;
        }
    }
}
