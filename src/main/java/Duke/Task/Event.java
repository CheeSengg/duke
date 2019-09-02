package Duke.Task;

public class Event extends Task {
    public final String SYMBOL = "[E]";
    protected String at;

    /**
     * Constructor for Event task
     * @param description The event name
     * @param date The dateTime of the event
     */
    public Event(String description, String date){
        super(description);
        this.at = date;
    }

    /**
     * @return return the expected format of String message for this task
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString() + " (at: " + this.at + ")\n";
    }
}
