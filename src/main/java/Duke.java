import Duke.Task.*;
import Duke.Exception.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String SPACES = "   __________________________________\n";

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        dukeLoadFile(tasks);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeInit(tasks);
    }

    private static void dukeLoadFile(ArrayList<Task> tasks){
        try {
            File currentDir = new File(System.getProperty("user.dir"));
            File loadFile = new File(currentDir.toString() + "\\src\\main\\data\\duke.txt");
            DateFormatter dateFormatter;
            loadFile.getParentFile().mkdirs();
            loadFile.createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(loadFile));

            String message;

            while((message = br.readLine()) != null){
                String[] arrStr = message.split(" ", 2);
                String[] splitStr;
                if(arrStr[0].toLowerCase().equals("todo")){
                    tasks.add(new Todo(arrStr[1]));
                } else if(arrStr[0].toLowerCase().equals("deadline")){
                    splitStr = arrStr[1].split("/by ", 2);
                    dateFormatter = new DateFormatter(splitStr[1]);
                    tasks.add(new Deadline(splitStr[0], dateFormatter.getDate() + dateFormatter.getTime()));
                } else if(arrStr[0].toLowerCase().equals("event")){
                    splitStr = arrStr[1].split("/at ", 2);
                    dateFormatter = new DateFormatter(splitStr[1]);
                    tasks.add(new Event(splitStr[0], dateFormatter.getDate() + dateFormatter.getTime()));
                } else if(arrStr[0].toLowerCase().equals("done")){
                    int taskNo = Integer.parseInt(arrStr[1]) - 1;
                    tasks.get(taskNo).markAsDone();
                }
            }

        } catch (IOException e){
            System.out.println(SPACES + "    ☹ OOPS!!! Unable to load file.\n" + SPACES);
        }
    }

    private static void dukeWriteFile(String message){
        try {
            File currentDir = new File(System.getProperty("user.dir"));
            File writeFile = new File(currentDir.toString() + "\\src\\main\\data\\duke.txt");
            FileWriter wr = new FileWriter(writeFile, true);

            wr.write(message + "\n" );
            wr.close();

        } catch (IOException e){
            System.out.println(SPACES + "    ☹ OOPS!!! File does not exist.\n" + SPACES);
        }
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
        Task task;
        String[] arrStr = message.split(" ", 2);
        String[] splitStr;
        DateFormatter dateFormatter;

        try {
            if(arrStr.length == 1 || arrStr[1].isEmpty())
                throw new DukeException(SPACES + "    ☹ OOPS!!! That is an invalid input\n" +
                        "    Please try again. \n" + SPACES);
            switch (arrStr[0].toLowerCase()) {
                case "todo":
                    task = new Todo(arrStr[1]);
                    break;
                case "deadline":
                    if(!message.contains("/by "))
                        throw new DukeException(SPACES + "    ☹ OOPS!!! You did not key in the deadline.\n" +
                                "    Please try again. \n" + SPACES);
                    splitStr = arrStr[1].split("/by ", 2);
                    dateFormatter = new DateFormatter(splitStr[1]);
                    if(!dateFormatter.isValidDateTime())
                        throw new DukeException(SPACES + "    ☹ OOPS!!! You did not key a valid deadline.\n" +
                                "    Please try again. \n" + SPACES);

                    task = new Deadline(splitStr[0], dateFormatter.getDate() + dateFormatter.getTime());
                    break;
                case "event":
                    if(!message.contains("/at "))
                        throw new DukeException(SPACES + "    ☹ OOPS!!! You did not key in the event time.\n" +
                                "    Please try again. \n" + SPACES);
                    splitStr = arrStr[1].split("/at ", 2);
                    dateFormatter = new DateFormatter(splitStr[1]);
                    if(!dateFormatter.isValidDateTime())
                        throw new DukeException(SPACES + "    ☹ OOPS!!! You did not key in a valid event time.\n" +
                                "    Please try again. \n" + SPACES);

                    task = new Event(splitStr[0], dateFormatter.getDate() + dateFormatter.getTime());
                    break;
                default:
                    throw new DukeException(SPACES + "    ☹ OOPS!!! You did not specify the type of task.\n" +
                            "    Please key in the task then description. \n" + SPACES);
            }

            dukeWriteFile(message);

            tasks.add(task);
            System.out.println(SPACES + "    Got it. I've added this task:\n"
                    + "     " + task.toString());
            System.out.println("    Now you have " + tasks.size() + " tasks in your list.\n"
                    + SPACES);

        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }

    //Set task as done
    private static void dukeSetDone(String message, ArrayList<Task> tasks){
        final String DONE = "    Nice! I've marked this task as done:\n";
        int taskNo;
        String[] arrStr = message.split(" ");

        try {
            if (arrStr.length == 1)
                throw new DukeException(SPACES + "    ☹ OOPS!!! You did not enter the task number.\n" +
                        "    To mark as done please key in a task number. \n" + SPACES);
            taskNo = Integer.parseInt(arrStr[1]) - 1;

            if(tasks.size() <= taskNo)
                throw new DukeException(SPACES + "    ☹ OOPS!!! That is an invalid task number.\n" +
                        "    To mark as done please key in a valid task number. \n" + SPACES);

            Task task = tasks.get(taskNo);

            if(task.isCompleted()) {
                throw new DukeException(SPACES + "    ☹ OOPS!!! This task has already been completed\n" + SPACES);
            }

            task.markAsDone();
            dukeWriteFile(message);

            System.out.println(SPACES + DONE + "      " + task.toString() + "\n" + SPACES);

        } catch (NumberFormatException e){
            System.out.println(SPACES + "    ☹ OOPS!!! You did not enter the task number.\n" +
                    "    To mark as done please key in a valid task number. \n" + SPACES);
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }

    private static void dukeDeleteTask(String message, ArrayList<Task> tasks){
        final String DELETE = "    Noted. I've removed this task:\n";
        int taskNo;
        String[] arrStr = message.split(" ");

        try{
            if (arrStr.length == 1)
                throw new DukeException(SPACES + "    ☹ OOPS!!! You did not enter the task number.\n" +
                        "    To mark as done please key in a task number. \n" + SPACES);
            taskNo = Integer.parseInt(arrStr[1]) - 1;

            if(tasks.size() <= taskNo)
                throw new DukeException(SPACES + "    ☹ OOPS!!! That is an invalid task number.\n" +
                        "    To mark as done please key in a valid task number. \n" + SPACES);

            dukeWriteFile(message);

            System.out.println(SPACES + DELETE + "      " + tasks.get(taskNo).toString() + "\n" + SPACES);

            tasks.remove(taskNo);

        } catch (NumberFormatException e){
            System.out.println(SPACES + "    ☹ OOPS!!! You did not enter the task number.\n" +
                    "    To mark as done please key in a valid task number. \n" + SPACES);
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }

    //start up duke
    private static void dukeInit(ArrayList<Task> tasks){
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
                case "delete":
                    dukeDeleteTask(message,tasks);
                    break;
                default:
                    dukeAddTask(message,tasks);
            }
        }
        scanner.close();
    }
}




//    private static void dukeAddTask(String message, ArrayList<Task> tasks){
//        Task task;
//        String[] arrStr = message.split(" ");
//        String[] splitStr;
//
//        try{
//
//            if (arrStr.length == 1)
//                throw new DukeException("    ☹ OOPS!!! You did not key in your task.\n" +
//                        "    Please enter a valid action: (Task)(Action)");
//
//            switch (arrStr[0].toLowerCase()){
//                case "todo":
//                    task = new Todo(arrStr[1]);
//                    break;
//                case "deadline":
//                    splitStr = message.split("/", 2);
//                    task = new Deadline(splitStr[0], splitStr[1]);
//                    break;
//                case "event":
//                    splitStr = arrStr[1].split("/", 2);
//                    task = new Event(splitStr[0], splitStr[1]);
//                    break;
//                default:
//                    throw new DukeException("    ☹ OOPS!!! That is an invalid task number.\n" +
//                            "    Please enter a valid task: (Task) (Action)");
//            }
//        } catch (DukeException e) {
//            System.out.println(e.getMessage());
//            task = DukeExceptionHandler.DukeAddTaskExceptionHandler();
//        }
//
//        tasks.add(task);
//        System.out.println(SPACES + "    Got it. I've added this task:\n"
//                + "     " + task.toString());
//        System.out.println("    Now you have " + tasks.size() + " tasks in your list.\n"
//                + SPACES);
//    }

//    private static void dukeSetDone(String message, ArrayList<Task> tasks){
//        final String DONE = "    Nice! I've marked this task as done:\n";
//        int taskNo;
//        String[] arrStr = message.split(" ");
//
//        try {
//            if (arrStr.length == 1)
//                throw new DukeException("    ☹ OOPS!!! You did not enter the task number.\n" +
//                        "    Please enter the task number: ");
//            taskNo = Integer.parseInt(arrStr[1]) - 1;
//        } catch (NumberFormatException e){
//            System.out.println("    ☹ OOPS!!! You did not enter the task number.\n" +
//                    "    Please enter the task number: ");
//            taskNo = DukeExceptionHandler.DukeMarkAsDoneExceptionHandler();
//        } catch (DukeException e){
//            System.out.println(e.getMessage());
//            taskNo = DukeExceptionHandler.DukeMarkAsDoneExceptionHandler();
//        }
//
//        while(tasks.size() <= taskNo) {
//            try {
//                throw new DukeException("    ☹ OOPS!!! That is an invalid task number.\n" +
//                        "    Please enter a valid task number: ");
//            } catch (DukeException e) {
//                System.out.println(e.getMessage());
//                taskNo = DukeExceptionHandler.DukeMarkAsDoneExceptionHandler();
//            }
//        }
//
//
//        Task task = tasks.get(taskNo);
//        task.markAsDone();
//
//        System.out.println(SPACES + DONE + "      " + task.toString() + "\n" + SPACES);
//
//    }
