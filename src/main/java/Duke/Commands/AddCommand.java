package Duke.Commands;

import Duke.Constant.Duke_Response;
import Duke.Storage;
import Duke.Task.*;
import Duke.Ui;
import Duke.DateFormatter;


public class AddCommand extends Command {
    private String taskType;
    private String description;
    private String dateTime;

    /**
     * Constructor for ToDo task
     * There is no dateTime for this type of task
     * @param taskType The task type to be added
     * @param description The description of task
     */
    public AddCommand(String taskType, String description){
        this.taskType = taskType;
        this.description = description;
        this.dateTime = "";
    }

    /**
     * Constructor for Event and Deadline Task
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
     * To append new task to the arraylist
     * @param tasks The arraylist of task stored by Duke
     * @param ui The user interface that handles messages
     * @param storage The database to read files and write txt files
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task;
        DateFormatter dateFormatter = new DateFormatter(dateTime);
        switch (taskType.toLowerCase()){
            case "todo":
                task = new Todo(description);
                tasks.add(task);
                ui.setMessage(new Duke_Response().ADD + "      " + task.toString()
                        + "\n    Now you have " + tasks.size() + " tasks in your list.\n");
                break;
            case "event":
                task = new Event(description, dateFormatter.getDateTime());
                tasks.add(task);
                ui.setMessage(new Duke_Response().ADD + "      " + task.toString()
                        + "\n    Now you have " + tasks.size() + " tasks in your list.\n");
                break;
            case "deadline":
                task = new Deadline(description, dateFormatter.getDateTime());
                tasks.add(task);
                ui.setMessage(new Duke_Response().ADD + "      " + task.toString()
                        + "\n    Now you have " + tasks.size() + " tasks in your list.\n");
                break;
        }
    }
}
