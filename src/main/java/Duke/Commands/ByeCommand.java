package Duke.Commands;

import Duke.Task.TaskList;

public class ByeCommand extends Command{
    @Override
    public void execute(TaskList tasks) {

    }

    /**
     * Exit program
     * @return quit = true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
