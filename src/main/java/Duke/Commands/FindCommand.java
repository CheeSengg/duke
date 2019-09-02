package Duke.Commands;

import Duke.Constant.Duke_Response;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String item;

    /**
     * Constructor for FindCommand
     * @param item The String input by user for searching of task
     */
    public FindCommand(String item){
        this.item = item;
    }

    /**
     * Finds all the tasks that contains the word that the user input
     * @param tasks The arraylist of task stored by Duke
     * @param ui The user interface that handles messages
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ArrayList<Task> foundTask = tasks.findTask(item);
        String message = "";

        if(foundTask.isEmpty()){
            message = new Duke_Response().NOT_FOUND;
        } else {
            message = new Duke_Response().FIND_FOUND;
            int counter = 1;
            for(Task i : foundTask){
                message += "      " + counter + "." + i.toString();
                counter++;
            }
        }
        ui.setMessage(message);
    }
}
