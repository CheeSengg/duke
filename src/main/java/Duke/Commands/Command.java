package Duke.Commands;

import Duke.Task.TaskList;

public abstract class Command {
    protected boolean quit;

    public abstract void execute(TaskList tasks);

    public boolean isExit(){
        return this.quit;
    }
}
