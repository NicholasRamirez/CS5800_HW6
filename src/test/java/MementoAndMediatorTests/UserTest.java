package MementoAndMediatorTests;

import MementoAndMediator.ChatServer;
import MementoAndMediator.Message;
import MementoAndMediator.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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
    void sendReceiveRecordMessageTest() {
        a.sendMessage(List.of("Kevin"), "Yo");
        assertEquals("Yo", server.getChat("Kevin").get(0).getContent());
        assertNotNull(a.getHistory().getLastMessage());
    }

    @Test
    void undoLastMessageTest() {
        a.sendMessage(List.of("Kevin"), "Yo");
        Message last = a.getHistory().getLastMessage();
        assertNotNull(last);

        boolean undo = a.undoLastMessage();
        assertTrue(undo);

        assertFalse(server.getChat("Kevin").contains(last));
        assertNull(a.getHistory().getLastMessage());
    }

    @Test
    void blockAndUnblockTest() {
        a.block("Kevin");
        b.sendMessage(List.of("Kyle"), "Yo");
        assertTrue(server.getChat("Kyle").isEmpty());

        a.unblock("Kevin");
        b.sendMessage(List.of("Kyle"), "Yo");
        assertEquals(1, server.getChat("Kyle").size());
        assertEquals("Yo", server.getChat("Kyle").get(0).getContent());
    }
}
