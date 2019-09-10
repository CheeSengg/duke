package Duke.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    Event event = new Event("Test String", "Test date");

    @Test
    void toString1() {
        assertEquals(event.toString(),"[E][\u2718] Test String (at: Test date)\n" );
    }

    @Test
    void getSymbol() {
        assertEquals(event.getSymbol(), "[E]");
    }

    @Test
    void writeToFile() {
        assertEquals(event.writeToFile(), "E | 0 | Test String | Test date");
    }
}