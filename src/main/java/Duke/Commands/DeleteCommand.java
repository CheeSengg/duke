package Duke.Commands;

import Duke.Constant.Duke_Response;
import Duke.Storage;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui;

public class DeleteCommand extends Command{
    private int index;

    /**
     * Creates DeleteCommand with the specified index
     * to be deleted. Index starts from 1.
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index){
        this.index = index;
    }

    /**
     * Deletes the task specified by user. Sets message of Ui
     * to show if command is successfully carried out.
     * @param tasks The list of task stored by Duke
     * @param ui The user interface that handles messages
     * @param storage The database to read files and write txt files
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message;
        if(index <= tasks.size() && index > 0) {
            Task task = tasks.deleteTask(index);
            message = new Duke_Response().DELETE_FOUND + task.toString() + "\n";
        } else{
            message = new Duke_Response().NOT_FOUND;
        }

        ui.setMessage(message);
    }
}
