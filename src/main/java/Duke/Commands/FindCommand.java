package Duke.Commands;

import Duke.Task.Task;
import Duke.Task.TaskList;

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
     * Print out all the task that contains the item String
     * @param tasks ArrayList of task
     */
    @Override
    public void execute(TaskList tasks) {
        ArrayList<Task> foundTask = tasks.findTask(item);

        if(foundTask.isEmpty()){
            System.out.println("      Sorry! The task that you are finding does not exist.\n");
        } else {
            int counter = 1;
            for(Task i : foundTask){
                System.out.println("      " + counter + "." + i.toString() + "\n");
                counter++;
            }
        }
    }
}
