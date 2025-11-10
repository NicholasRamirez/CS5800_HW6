package Iterator;

import java.util.*;

public class ChatServer {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Set<String>> blocked = new HashMap<>();

    public void register(User user) {
        users.put(user.getName(), user);
        blocked.putIfAbsent(user.getName(), new HashSet<>());
    }

    public void unregister(String username) {
        users.remove(username);
        blocked.remove(username);
    }

    public void block(String recipient, String sender) {
        blocked.putIfAbsent(recipient, new HashSet<>());
        blocked.get(recipient).add(sender);
    }

    public void unblock(String recipient, String sender) {
        Set<String> list = blocked.get(recipient);
        if (list != null) list.remove(sender);
    }

    public void sendMessage(Message message) {
        for (String recipient : message.getRecipients()) {
            if (isBlocked(recipient, message.getSender()))
                continue;

            User receiver = users.get(recipient);

            if (receiver != null)
                receiver.receiveMessage(message);
        }

        User sender = users.get(message.getSender());
        if (sender != null) {
            sender.recordMessage(message);
            sender.receiveMessage(message);
        }
    }

    private boolean isBlocked(String recipient, String sender) {
        Set<String> list = blocked.get(recipient);
        return list != null && list.contains(sender);
    }

    public boolean undo(String senderName, Message message) {
        if (senderName == null || message == null) return false;
        if (!senderName.equals(message.getSender())) return false;
        for (User user : users.values()) {
            user.getHistory().removeFromChatHistory(message);
        }
        return true;
    }

    // Display chat
    public List<Message> getChat(String name) {
        User user = users.get(name);
        return (user == null) ? List.of() : user.getHistory().getChatHistory();
    }
}