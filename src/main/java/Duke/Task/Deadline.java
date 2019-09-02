package Duke.Task;

public class Deadline extends Task{
    public final String SYMBOL = "[D]";
    protected String by;

    /**
     * Constructor for Deadline Task
     * @param description The deadline's task name
     * @param by The dateTime of the deadline
     */
    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    /**
     * @return the expected format of String message for this task
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString() + " (by: " + this.by + ")\n";
    }
}
