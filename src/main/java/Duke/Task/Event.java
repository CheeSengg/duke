package Duke.Task;

public class Event extends Task {
    final String SYMBOL = "[E]";
    protected String date;

    public Event(String description, String date){
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString() + " (at: " + this.date + ")";
    }
}
