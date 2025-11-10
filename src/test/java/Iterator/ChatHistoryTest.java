package Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

public class ChatHistoryTest {

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
    void iteratorTest() {
        a.sendMessage(List.of("Kevin"), "Yo");
        b.sendMessage(List.of("Kyle"), "Yo");
        c.sendMessage(List.of("Kyle"), "Whats up Kyle");

        Iterator iterator = a.getHistory().iterator(b);
        int count = 0;
        while (iterator.hasNext()) {
            Message message = (Message) iterator.next();
            assertTrue(message.getSender().equals("Kevin") || message.getRecipients().contains("Kevin"));
            count++;
        }
        assertEquals(2, count);
    }
}
