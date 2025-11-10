package MementoAndMediator;

import java.util.List;

public class Driver {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        User kyle = new User("Kyle", server);
        User kevin  = new User("Kevin", server);
        User joe  = new User("Joe", server);

        server.register(kyle);
        server.register(kevin);
        server.register(joe);

        System.out.println("1. Users can send messages to one or more other users through the chat server");
        // Kyle, Kevin, and Joe send messages to each other
        kyle.sendMessage(List.of("Kevin", "Joe"), "Yo who is tryna hang later?");
        kevin.sendMessage(List.of("Kyle", "Joe"), "Im down whats the move");
        joe.sendMessage(List.of("Kyle", "Kevin"), "Who would drive?");

        printChat(server, "Kyle");
        printChat(server, "Kevin");
        printChat(server, "Joe");

        System.out.println("\n2. Users can undo the last message they sent");
        // Joe's messages are gone
        boolean undo = joe.undoLastMessage();
        printChat(server, "Kyle");
        printChat(server, "Kevin");
        printChat(server, "Joe");

        System.out.println("\n3. Users can block messages from specific user");
        // Joe blocks Kevin and cant see his new message
        joe.block("Kevin");
        kevin.sendMessage(List.of("Joe"), "Can I borrow 10 dollars?");
        printChat(server, "Joe");

        System.out.println("\n4. Users can receive messages from other users and view the chat history for a specific user");
        // Kyle sends Joe a new message
        kyle.sendMessage(List.of("Joe"), "Yo Joe whats up");
        printChat(server, "Joe");
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
