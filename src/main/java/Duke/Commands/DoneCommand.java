package Duke.Commands;

import Duke.Constant.Duke_Response;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui;

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
     * Mark the task that is input by user as done
     * @param tasks The arraylist of task stored by Duke
     * @param ui The user interface that handles messages
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        String message;

        if(tasks.size() >= index){
            if(!tasks.isCompletedTask(index)) {
                Task task = tasks.doneTask(index);
                message = new Duke_Response().DONE_FOUND + "      " + task.toString() + "\n";
            } else {
                message = new Duke_Response().DONE_COMPLETED;
            }
        } else {
            message = new Duke_Response().NOT_FOUND;
        }

        ui.setMessage(message);
    }
}
