package MementoAndMediatorTests;

import MementoAndMediator.ChatHistory;
import MementoAndMediator.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.List;

public class ChatHistoryTest {
    @Test
    void saveAndGetLastMessageTest() {
        ChatHistory chatHistory = new ChatHistory();
        Message msg = new Message("Kyle", List.of("Kevin"), "Yo", Instant.now());
        assertNull(chatHistory.getLastMessage());
        chatHistory.saveMessage(msg);
        assertSame(msg, chatHistory.getLastMessage());
    }

    @Test
    void clearLastMessageAndMemTest() {
        ChatHistory chatHistory = new ChatHistory();
        Message msg = new Message("Kyle", List.of("Kevin"), "Yo", Instant.now());
        chatHistory.saveMessage(msg);
        chatHistory.clearLastMessageAndMem();
        assertNull(chatHistory.getLastMessage());
    }

    @Test
    void addAndRemoveChatHistoryTest() {
        ChatHistory chatHistory = new ChatHistory();
        Message msg = new Message("Kyle", List.of("Kevin"), "Yo", Instant.now());
        Message msg2 = new Message("Kevin", List.of("Kyle"), "Yo", Instant.now());

        chatHistory.addToChatHistory(msg);
        chatHistory.addToChatHistory(msg2);
        assertEquals(2, chatHistory.getChatHistory().size());

        chatHistory.removeFromChatHistory(msg);
        List<Message> messages = chatHistory.getChatHistory();
        assertEquals(1, messages.size());
        assertSame(msg2, messages.get(0));
    }
}
