package Duke.Commands;

import Duke.Task.Task;
import Duke.Task.TaskList;

public class DeleteCommand extends Command{
    private int index;

    /**
     * Constructor for DeleteCommand
     * @param index The task to be deleted, index of user's list starts from 1
     */
    public DeleteCommand(int index){
        this.index = index;
    }

    /**
     * Prints the task denoted by user to be deleted
     * @param tasks ArrayList of task
     */
    @Override
    public void execute(TaskList tasks) {
        Task task = tasks.deleteTask(index);

        System.out.println("      " + task.toString() + "\n");
    }
}
