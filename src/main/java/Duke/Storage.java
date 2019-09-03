package Duke;

import Duke.Task.*;

import java.io.*;

public class Storage {
    private File filePath;

    /**
     * Constructor for Storage Class
     * Create directory and file in the event that it does not exist
     * @param filePath File, the path of the duke.txt File
     */
    public Storage(File filePath){
        this.filePath = filePath;
        try {
            if(!filePath.getParentFile().exists())
                filePath.getParentFile().mkdirs();
            if(!filePath.exists())
                filePath.createNewFile();
        } catch (IOException e){
            System.out.println("Unable to create file.\n");
        }
    }

    /**
     * Read all the tasks that are stored in the duke.txt and load into TaskList
     * @return TaskList with all the tasks that were stored in the duke.txt file
     */
    public TaskList load(){
        TaskList tasks = new TaskList();
        try {
            FileReader rd = new FileReader(filePath);
            BufferedReader br = new BufferedReader(rd);

            String message;
            int counter = 0;

            while((message = br.readLine()) != null){
                String[] arrStr = message.split(" \\| ");

                if(arrStr.length == 3){
                    tasks.add(new Todo(arrStr[2]));
                } else if (arrStr[0].equals("D")){
                    tasks.add(new Deadline(arrStr[2], arrStr[3]));
                } else {
                    tasks.add(new Event(arrStr[2], arrStr[3]));
                }

                if(arrStr[1].equals("1")){
                    tasks.get(counter).markAsDone();
                }

                counter++;
            }

            br.close();
            rd.close();

        } catch (IOException e){
            System.out.println("Unable to load file.\n");
        }

        return tasks;
    }

    /**
     * Write to duke.txt all the tasks that has been stored in TaskList
     * @param tasks The TaskList with all the added tasks. To be written into txt file for storage
     */
    public void write(TaskList tasks){

        try {
            filePath.delete();
            filePath.createNewFile();
            FileWriter wr = new FileWriter(filePath, true);

            for(Task i : tasks){
                wr.write(i.writeToFile() + "\n");
            }
            wr.close();
        } catch (IOException e){
            System.out.println(" BYE, sorry you suck.");
        }
    }
}
