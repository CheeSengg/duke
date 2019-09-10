package Duke.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TodoTest {
    Todo todo = new Todo("This is a test");

    @Test
    void toString1() {
        assertEquals(todo.toString(), "[T][\u2718] This is a test\n" );
    }

    @Test
    void getSymbol() {
        assertEquals(todo.getSymbol(), "[T]");
    }

    @Test
    void writeToFile() {
        assertEquals(todo.writeToFile(), "T | 0 | This is a test");
    }
}