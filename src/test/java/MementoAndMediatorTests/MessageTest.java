package MementoAndMediatorTests;

import MementoAndMediator.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.List;

public class MessageTest {
    @Test
    void messageTest() {
        Instant now = Instant.now();
        Message msg = new Message("Kyle", List.of("Kevin", "Joe"), "Yo", now);

        assertEquals("Kyle", msg.getSender());
        assertEquals(List.of("Kevin", "Joe"), List.copyOf(msg.getRecipients()));
        assertEquals("Yo", msg.getContent());
        assertNotNull(msg.getTimestamp());
    }

    @Test
    void toStringTest() {
        Message msg = new Message("Kyle", List.of("Kevin", "Joe"), "Yo", Instant.now());
        assertTrue(msg.toString().contains("Yo"));
        assertTrue(msg.toString().contains("Kyle"));
    }
}
