package Duke.Commands;

import Duke.Task.TaskList;
import Duke.Ui;

public abstract class Command {
    protected boolean quit;

    /**
     * @param tasks The arraylist of task stored by Duke
     * @param ui The user interface that handles messages
     */
    public abstract void execute(TaskList tasks, Ui ui);

    /**
     * Duke does not stop running
     * @return quit = false
     */
    public boolean isExit(){
        return false;
    }
}
