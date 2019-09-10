package Duke.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
    Deadline deadline = new Deadline("Test String", "Test date");

    @Test
    void toString1() {
        assertEquals(deadline.toString(), "[D][\u2718] Test String (by: Test date)\n");
    }

    @Test
    void getSymbol() {
        assertEquals(deadline.getSymbol(), "[D]");
    }

    @Test
    void writeToFile() {
        deadline.markAsDone();
        assertEquals(deadline.writeToFile(), "D | 1 | Test String | Test date");
    }
}