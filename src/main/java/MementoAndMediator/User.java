package MementoAndMediator;

import java.time.Instant;
import java.util.*;

public class User {
    private final String name;
    private final ChatServer server;
    private final ChatHistory history = new ChatHistory();

    public User(String name, ChatServer server) {
        this.name = name;
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public ChatHistory getHistory() {
        return history;
    }

    public void sendMessage(Collection<String> recipients, String content) {
        if (recipients == null || recipients.isEmpty())
            return;

        Message message = new Message(name, recipients, content, Instant.now());
        server.sendMessage(message);
    }

    public void receiveMessage(Message message) {
        history.addToChatHistory(message);
    }

    public void recordMessage(Message message) {
        history.saveMessage(message);
    }

    public boolean undoLastMessage() {
        Message lastMessage = history.getLastMessage();
        if (lastMessage == null)
            return false;

        boolean undo = server.undo(name, lastMessage);
        if (undo) history.clearLastMessageAndMem();
        return undo;
    }

    public void block(String otherUser) {
        server.block(name, otherUser);
    }

    public void unblock(String otherUser) {
        server.unblock(name, otherUser);
    }
}
