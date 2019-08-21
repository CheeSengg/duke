public class Task {
    private String description;
    private boolean isCompleted = false;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isCompleted = true;
    }

    public String getStatusIcon(){
        return (isCompleted ? "\u2713" : "\u2718");
    }
}
