import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String SPACES = "   __________________________________\n";

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        levelOne();
//        levelTwo();
        levelThree();
    }

    private static void dukeGreet(){
        final String greetings = "    Hello! I'm Duke\n"
                + "    What can I do for you?\n";

        System.out.println(SPACES + greetings + SPACES);
    }

    private static void dukeBye(){
        final String bye = "    Bye. Hope to see you again soon!\n";

        System.out.println(SPACES + bye + SPACES);
    }

    private static void dukeEcho(String message) {
        System.out.println(SPACES + "    " + message + "\n" + SPACES);
    }

    private static void levelOne(){
        //To get message for echo
        Scanner scanner = new Scanner(System.in);

        //Detect bye
        boolean end = false;

        dukeGreet();
        while(!end){
            //Scan messages
            String message = scanner.nextLine();

            //check if input String is bye
            if(message.toLowerCase().equals("bye")){
                end = true;
                dukeBye();
            }else{
                dukeEcho(message);
            }
        }

        scanner.close();
    }

    private static void dukeListTask(ArrayList<String> tasks){
        System.out.print(SPACES);
        for(int i = 0; i < tasks.size(); i++){
            System.out.println("    " + (i+1) + ". " + tasks.get(i));
        }
        System.out.println(SPACES);
    }

    private static void dukeAddTask(String message, ArrayList<String> tasks){
        tasks.add(message);
        System.out.println(SPACES + "    added: " + message + "\n" + SPACES);
    }

    private static void levelTwo(){
        ArrayList<String> tasks = new ArrayList<>();
        String message;
        boolean end = false;

        Scanner scanner = new Scanner(System.in);

        dukeGreet();
        while(!end){
            message = scanner.nextLine();

            switch (message.toLowerCase()){
                case "bye":
                    dukeBye();
                    end = true;
                    break;
                case "list":
                    dukeListTask(tasks);
                    break;
                default:
                    dukeAddTask(message, tasks);
            }
        }

        scanner.close();

    }

    private static void dukeListTaskThree(ArrayList<Task> tasks){
        System.out.print(SPACES);
        for(int i = 0; i < tasks.size(); i++){
            System.out.println("    " + (i+1)
                    + ".[" + tasks.get(i).getStatusIcon() + "] "
                    + tasks.get(i).getDescription());
        }
        System.out.println(SPACES);
    }

    private static void dukeAddTaskThree(String message, ArrayList<Task> tasks){
        Task task = new Task(message);
        tasks.add(task);
        System.out.println(SPACES + "    added: " + message + "\n" + SPACES);
    }

    private static void dukeSetDone(String message, ArrayList<Task> tasks){
        final String DONE = "    Nice! I've marked this task as done:\n";

        String[] arrStr = message.split(" ",2);
        int taskNo = Integer.parseInt(arrStr[1]) - 1;

        Task task = tasks.get(taskNo);
        task.markAsDone();

        System.out.println(SPACES + DONE
                + "      [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + SPACES);

    }

    private static void levelThree(){
        ArrayList<Task> tasks = new ArrayList<>();
        String message;
        boolean end = false;

        Scanner scanner = new Scanner(System.in);

        dukeGreet();
        while(!end){
            message = scanner.nextLine();

            switch (message.toLowerCase()){
                case "bye":
                    dukeBye();
                    end = true;
                    break;
                case "list":
                    dukeListTaskThree(tasks);
                    break;
                default:
                    if(message.toLowerCase().contains("done")){
                        dukeSetDone(message, tasks);
                    } else {
                        dukeAddTaskThree(message, tasks);
                    }
            }
        }

        scanner.close();
    }
}
