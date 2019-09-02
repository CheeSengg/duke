package Duke.Commands;

import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.TaskList;
import Duke.Task.Todo;

public class AddCommand extends Command {
    private String taskType;
    private String description;
    private String dateTime;

    /**
     * Constructer for ToDo task
     * There is no dateTime for this type of task
     * @param taskType The task type to be added
     * @param description The description of task
     */
    public AddCommand(String taskType, String description){
        this.taskType = taskType;
        this.description = description;
        this.dateTime = null;
    }

    /**
     * Constructer for Event and Deadline Task
     * @param taskType The task type to be added
     * @param description The description of task
     * @param dateTime The date and time of task to either be completed or attend to
     */
    public AddCommand(String taskType, String description, String dateTime){
        this.taskType = taskType;
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * To append the new task to the arraylist
     * @param tasks the arraylist of task by user
     */
    @Override
    public void execute(TaskList tasks) {
        switch (taskType.toLowerCase()){
            case "todo":
                tasks.add(new Todo(description));
                break;
            case "event":
                tasks.add(new Event(description, dateTime));
                break;
            case "deadline":
                tasks.add(new Deadline(description, dateTime));
                break;
        }
    }
}
