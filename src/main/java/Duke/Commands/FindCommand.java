package Duke.Commands;

import Duke.Task.TaskList;

public class FindCommand extends Command{
    String item;

    public FindCommand(String item){
        this.item = item;
    }

    @Override
    public void execute(TaskList tasks) {

    }
}
