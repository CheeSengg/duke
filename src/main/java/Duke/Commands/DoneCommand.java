package Duke.Commands;

import Duke.Task.Task;
import Duke.Task.TaskList;

public class DoneCommand extends Command{
    private int index;

    /**
     * Constructor for DoneCommand
     * @param index The task to be set as done, index of user's list starts from 1
     */
    public DoneCommand(int index){
        this.index = index;
    }

    /**
     * Prints the task denoted by user to be mark as done
     * @param tasks ArrayList of task
     */
    @Override
    public void execute(TaskList tasks) {
        Task task = tasks.doneTask(index);

        System.out.println("      " + task.toString() + "\n");
    }
}
