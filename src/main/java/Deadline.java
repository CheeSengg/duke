public class Deadline extends Task{
    final String SYMBOL = "[D]";
    protected String by;

    Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString() + " (by: " + this.by + ")";
    }
}
