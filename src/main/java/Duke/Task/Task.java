package Duke.Task;

public abstract class Task {
    protected String description;
    private boolean isCompleted = false;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getDescription(){
        return description;
    }

    protected String getStatusIcon(){
        return (isCompleted ? "\u2713" : "\u2718");
    }

    public String toString(){
        return  "[" + getStatusIcon() + "] " + this.description;
    }
}
