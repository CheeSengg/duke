package Duke.Task;

public class
Todo extends Task {
    public final String SYMBOL = "[T]";

    /**
     * Constructor for Todo task
     * @param description The deadline's task name
     */
    public Todo(String description){
        super(description);
    }

    /**
     * @return the expected format of String message for this task
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString() + "\n";
    }
}
