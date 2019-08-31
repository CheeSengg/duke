package Duke.Task;

public class Todo extends Task {
    final String SYMBOL = "[T]";

    public Todo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString();
    }
}
