package Iterator;

import java.util.Iterator;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        User kyle = new User("Kyle", server);
        User kevin = new User("Kevin", server);
        User joe = new User("Joe", server);

        server.register(kyle);
        server.register(kevin);
        server.register(joe);

        kyle.sendMessage(List.of("Kevin", "Joe"), "Yo Whats up");
        kevin.sendMessage(List.of("Kyle"), "Hey Kyle");
        joe.sendMessage(List.of("Kyle"), "Whats up Kyle");

        printChat(server, "Kyle");
        printChat(server, "Kevin");
        printChat(server, "Joe");

        System.out.println("\nIterator: Messages in Kyle's history with Kevin");
        Iterator<Message> iterator = kyle.iterator(kevin);

        if (!iterator.hasNext()) {
            System.out.println("No messages with Kevin");
        } else  {
            while (iterator.hasNext()) {
                Message message = iterator.next();
                System.out.println(message);
            }
        }

    }

    private static void printChat(ChatServer server, String user) {
        System.out.println(user + "'s Chat:");
        var message = server.getChat(user);

        if (message.isEmpty()) {
            System.out.println("Empty");
        } else {
            for (var msg : message) {
                System.out.println("  " + msg);
            }
        }
    }
}
