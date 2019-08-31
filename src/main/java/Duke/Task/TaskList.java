package Duke.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    /**
     * Get the task for index based on 1
     * @param index the index of task to be queried, index starts from 1
     * @return The task with index starting from 0
     */
    public Task getTask(int index){

        return this.get(index - 1);
    }

    /**
     * Delete Task based on user input
     * @param index the index of task to be deleted, index starts from 1
     * @return The task that is deleted
     */
    public Task deleteTask(int index){

        return this.remove(index-1);
    }

    /**
     * Set Task as done based on user input
     * @param index the index of task to be set as done, index starts from 1
     * @return The task that is completed and set as done
     */
    public Task doneTask(int index){
        Task refTask = this.getTask(index);
        refTask.markAsDone();

        return refTask;
    }
}
