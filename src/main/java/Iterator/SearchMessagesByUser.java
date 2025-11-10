package Iterator;
import java.util.*;

public class SearchMessagesByUser implements Iterator<Message> {
    private final List<Message> matchMessage;
    private int index = 0;

    public SearchMessagesByUser(Collection<Message> messages, User userToSearchWith) {
        String name = userToSearchWith.getName();
        matchMessage = new ArrayList<>();

        List<Message> messageList = new ArrayList<>(messages);
        for (int i = 0; i < messageList.size(); i++) {
            Message message = messageList.get(i);

            if (message.getSender().equals(name) || message.getRecipients().contains(name)) {
                matchMessage.add(message);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return index < matchMessage.size();
    }

    @Override
    public Message next() {
        if (!hasNext()) throw new NoSuchElementException();
        return matchMessage.get(index++);
    }
}
