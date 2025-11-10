package Iterator;

import java.util.*;

public class ChatHistory implements IterableByUser {
    private Message lastMessage;
    private MessageMemento lastMemento;
    private final List<Message> chatHistory = new ArrayList<>();

    public void saveMessage(Message message) {
        lastMessage = message;
        lastMemento = new MessageMemento(message.getContent(), message.getTimestamp());
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void clearLastMessageAndMem() {
        lastMessage = null;
        lastMemento = null;
    }

    public void addToChatHistory(Message message) {
        chatHistory.add(message);
    }

    public List<Message> getChatHistory() {
        return new ArrayList<>(chatHistory);
    }

    public void removeFromChatHistory(Message target) {
        chatHistory.removeIf(message -> message == target);
    }

    @Override
    public Iterator iterator(User userToSearchWith) {
        return new SearchMessagesByUser(chatHistory, userToSearchWith);
    }
}
