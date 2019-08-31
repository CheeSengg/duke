package Duke.Commands;

import Duke.Task.TaskList;

public class DeleteCommand extends Command{
    int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks) {

    }
}
