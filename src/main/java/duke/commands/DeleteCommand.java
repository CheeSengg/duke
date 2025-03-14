package duke.commands;

import duke.constant.DukeResponse;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates DeleteCommand with the specified index
     * to be deleted. Index starts from 1.
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task specified by user. Sets message of Ui
     * to show if command is successfully carried out.
     * @param tasks The list of task stored by duke
     * @param ui The user interface that handles messages
     * @param storage The database to read files and write txt files
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message;
        if (index <= tasks.size() && index > 0) {
            Task task = tasks.deleteTask(index);
            message = new DukeResponse().DELETE_FOUND + task.toString() + "\n";
        } else {
            message = new DukeResponse().NOT_FOUND;
        }

        ui.setMessage(message);
    }
}
