package Duke.Task;

import Duke.DateFormatter;

public class Deadline extends Task{
    private final String SYMBOL = "[D]";
    private String by;

    /**
     * Constructor for Deadline Task
     * @param description The deadline's task name
     * @param by The dateTime of the deadline
     */
    public Deadline(String description, String by){
        super(description.trim());
        this.by = by;
    }

    /**
     * @return the expected format of String message for this task
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString() + " (by: " + new DateFormatter(this.by).getDateTime() + ")\n";
    }

    /**
     * Used when using storage.write
     * For reference to store the correct task type for each task
     * @return The Symbol to reference to their task type
     */
    @Override
    public String getSymbol() {
        return this.SYMBOL;
    }

    /**
     * The String format to be written into the duke.txt File for each task
     * @return String format for task to be written into the duke.txt File
     */
    @Override
    public String writeToFile() {
        return String.format("D | %d | %s | %s",  (isCompleted() ? 1 : 0), this.getDescription(), this.by);
    }
}
