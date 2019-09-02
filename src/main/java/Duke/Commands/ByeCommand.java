package Duke.Commands;

import Duke.Constant.Duke_Response;
import Duke.Task.TaskList;
import Duke.Ui;

public class ByeCommand extends Command{

    /**
     * Set messages by ui to BYE
     * @param tasks The arraylist of task stored by Duke
     * @param ui The user interface that handles messages
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.setMessage(new Duke_Response().BYE);
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
