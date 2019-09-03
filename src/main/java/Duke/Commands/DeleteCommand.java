package Duke.Commands;

import Duke.Constant.Duke_Response;
import Duke.Storage;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui;

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
     * Delete the task that is input by user
     * @param tasks The arraylist of task stored by Duke
     * @param ui The user interface that handles messages
     * @param storage The database to read files and write txt files
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message;
        if(tasks.size() >= index) {
            Task task = tasks.deleteTask(index);
            message = new Duke_Response().DELETE_FOUND + "      " + task.toString() + "\n";
        } else{
            message = new Duke_Response().NOT_FOUND;
        }

        ui.setMessage(message);
    }
}
