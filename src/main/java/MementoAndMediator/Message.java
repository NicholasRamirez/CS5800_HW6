package MementoAndMediator;

import java.time.Instant;
import java.util.*;

public class Message {
    private final String sender;
    private final Set<String> recipients;
    private final Instant timestamp;
    private final String content;

    public Message(String sender, Collection<String> recipients, String content, Instant timestamp) {
        this.sender = sender;
        this.recipients = new LinkedHashSet<>(recipients);
        this.timestamp = timestamp;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public Set<String> getRecipients() {
        return recipients;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Sender: " + sender + ", Recipients: " + recipients + ", Content: " + content + ", Timestamp: " + timestamp;
    }
}