import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String SPACES = "   __________________________________\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeInit();
    }

    //Greetings by duke when it is booting up
    private static void dukeGreet(){
        final String greetings = "    Hello! I'm Duke\n"
                + "    What can I do for you?\n";

        System.out.println(SPACES + greetings + SPACES);
    }

    //Goodbye by duke when invoked to shut down
    private static void dukeBye(){
        final String bye = "    Bye. Hope to see you again soon!\n";

        System.out.println(SPACES + bye + SPACES);
    }

    //For Level-1 to repeat what user input
    private static void dukeEcho(String message) {
        System.out.println(SPACES + "    " + message + "\n" + SPACES);
    }

    //List the task that were added into duke
    private static void dukeListTask(ArrayList<Task> tasks){
        System.out.print(SPACES);
        System.out.println("    Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++){
            System.out.println("    " + (i+1) + "." + tasks.get(i).toString());
        }
        System.out.println(SPACES);
    }

    //Add Task to be stored in duke
    private static void dukeAddTask(String message, ArrayList<Task> tasks){
        String[] arrStr = message.split(" ",2);
        Task task;
        String[] splitStr;

        switch (arrStr[0].toLowerCase()){
            case "todo":
                task = new Todo(arrStr[1]);
                break;
            case "deadline":
                splitStr = arrStr[1].split("/", 2);
                task = new Deadline(splitStr[0], splitStr[1]);
                break;
            case "event":
                splitStr = arrStr[1].split("/", 2);
                task = new Event(splitStr[0], splitStr[1]);
                break;
            default:
                task = new Task(message);
        }

        tasks.add(task);
        System.out.println(SPACES + "    Got it. I've added this task:\n"
                + "     " + task.toString());
        System.out.println("    Now you have " + tasks.size() + " tasks in your list.\n"
                + SPACES);
    }

    //Set task as done
    private static void dukeSetDone(String message, ArrayList<Task> tasks){
        final String DONE = "    Nice! I've marked this task as done:\n";

        String[] arrStr = message.split(" ",2);
        int taskNo = Integer.parseInt(arrStr[1]) - 1;

        Task task = tasks.get(taskNo);
        task.markAsDone();

        System.out.println(SPACES + DONE + "      " + task.toString() + "\n" + SPACES);

    }

    //start up duke
    private static void dukeInit(){
        ArrayList<Task> tasks = new ArrayList<>();
        String message;
        boolean end = false;

        Scanner scanner = new Scanner(System.in);

        dukeGreet();
        while(!end){
            message = scanner.nextLine();
            String[] arrStr = message.split(" ",2);

            switch (arrStr[0].toLowerCase()){
                case "bye":
                    dukeBye();
                    end = true;
                    break;
                case "list":
                    dukeListTask(tasks);
                    break;
                case "done":
                    dukeSetDone(message, tasks);
                    break;
                default:
                    dukeAddTask(message,tasks);
            }
        }

        scanner.close();
    }
}
