package MementoAndMediatorTests;

import MementoAndMediator.ChatServer;
import MementoAndMediator.Message;
import MementoAndMediator.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ChatServerTest {

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
    void registerAndUnregisterTest() {
        server.unregister("Kevin");
        a.sendMessage(List.of("Kevin"), "Yo");
        assertTrue(server.getChat("Kevin").isEmpty());
    }

    @Test
    void sendMessageTest() {
        a.sendMessage(List.of("Kevin", "Joe"), "Yo");
        assertTrue(server.getChat("Kevin").stream().anyMatch(s -> s.getContent().equals("Yo")));
        assertTrue(server.getChat("Joe").stream().anyMatch(s -> s.getContent().equals("Yo")));
        assertTrue(server.getChat("Kyle").isEmpty());
    }

    @Test
    void blockTest() {
        a.block("Kevin");
        b.sendMessage(List.of("Kyle"), "Yo");
        assertFalse(server.getChat("Kevin").stream().anyMatch(s -> s.getContent().equals("Yo")));
    }

    @Test
    void undoTest() {
        a.sendMessage(List.of("Kevin", "Joe"), "Yo");
        Message last = a.getHistory().getLastMessage();
        assertTrue(server.getChat("Kevin").contains(last));
        assertTrue(server.getChat("Joe").contains(last));

        boolean undo = a.undoLastMessage();
        assertTrue(undo);
        assertFalse(server.getChat("Kevin").contains(last));
        assertFalse(server.getChat("Joe").contains(last));
    }
}
