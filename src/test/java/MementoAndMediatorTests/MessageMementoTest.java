package MementoAndMediatorTests;

import MementoAndMediator.MessageMemento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

public class MessageMementoTest {
    @Test
    void messageMementoTest() {
        Instant now = Instant.now();
        MessageMemento msg = new MessageMemento("Yo", now);
        assertEquals("Yo", msg.getContent());
        assertEquals(now, msg.getTimestamp());
    }
}
