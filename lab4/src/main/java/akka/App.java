package akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create();
        ActorRef resultStoreActor = system.actorOf()
    }
}
