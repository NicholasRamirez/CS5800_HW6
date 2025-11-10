package Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.List;

public class UserTest {

    ChatServer server;
    User a, b, c;

    @BeforeEach
    void serverSetup() {
        server = new ChatServer();
        a = new User("Kyle", server);
        b = new User("Kevin", server);
        c = new User("Joe", server);

        server.register(a);
        server.register(b);
        server.register(c);
    }

    @Test
    void noMessagesTest() {
        Iterator iterator = a.iterator(b);
        assertFalse(iterator.hasNext());
    }

    @Test
    void sendMessageTest() {
        a.sendMessage(List.of("Kevin", "Joe"), "Yo Whats up");
        b.sendMessage(List.of("Kyle"), "Hey Kyle");
        c.sendMessage(List.of("Kyle"), "Whats up Kyle");
        Iterator<Message> iterator = a.iterator(b);
        assertTrue(iterator.hasNext());
    }

    @Test
    void excludeUnrelatedUsersTest() {
        a.sendMessage(List.of("Joe"), "Yo Whats up");
        c.sendMessage(List.of("Kyle"), "Hey Kyle");
        Iterator iterator = a.iterator(b);
        assertFalse(iterator.hasNext());
    }

    @Test
    void hasNextAndNextTest() {
        a.sendMessage(List.of("Kevin"), "Yo Whats up");
        a.sendMessage(List.of("Kevin"), "Yo Whats up 2");

        Iterator iterator = a.iterator(b);

        assertTrue(iterator.hasNext());
        Message first =  (Message) iterator.next();
        assertNotNull(first);

        assertTrue(iterator.hasNext());
        Message second =  (Message) iterator.next();
        assertNotNull(second);
        assertFalse(iterator.hasNext());
    }
}
