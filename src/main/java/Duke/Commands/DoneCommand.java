package Duke.Commands;

import Duke.Task.TaskList;

public class DoneCommand extends Command{
    int index;

    public DoneCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks) {

    }
}
