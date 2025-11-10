package Iterator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchMessagesByUserTest {

    @Test
    public void searchMessagesByUserTest() {
        ChatServer server = new ChatServer();
        User a = new User("Kyle", server);
        User b = new User("Kevin", server);

        Message message = new Message("kyle", List.of("Kevin"), "Yo", Instant.now());
        SearchMessagesByUser iterator = new SearchMessagesByUser(List.of(message), b);

        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
