package Duke.Commands;

import Duke.Task.Task;
import Duke.Task.TaskList;

public class ListCommand extends Command{

    /**
     * List out all the task, listing of task starts from 1
     * @param tasks ArrayList of task
     */
    @Override
    public void execute(TaskList tasks) {

        int counter = 1;
        for(Task i : tasks){
            System.out.println("    " + counter + "." + i.toString());
            counter++;
        }

        for(int i = 0; i < tasks.size(); i++){
            System.out.println("    " + (i+1) + "." + tasks.get(i).toString());
        }
    }
}
