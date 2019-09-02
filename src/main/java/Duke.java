import Duke.Commands.Command;
import Duke.Constant.Duke_Response;
import Duke.Exception.DukeException;
import Duke.Parser.Parser;
import Duke.Task.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static TaskList tasks = new TaskList();
    

    public static void main(String[] args) {
        dukeLoadFile();



        System.out.println("Hello from\n" + Duke_Response.LOGO);


        run();
//        dukeInit(tasks);
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = sc.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e){
                System.out.println("Invalid Command");
            }
        }

        sc.close();
    }

    private static void dukeLoadFile(){
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
            System.out.println(Duke_Response.SPACES + "    ☹ OOPS!!! Unable to load file.\n" + Duke_Response.SPACES);
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
            System.out.println(Duke_Response.SPACES + "    ☹ OOPS!!! File does not exist.\n" + Duke_Response.SPACES);
        }
    }

    //Greetings by duke when it is booting up
    private static void dukeGreet(){
        final String greetings = "    Hello! I'm Duke\n"
                + "    What can I do for you?\n";

        System.out.println(Duke_Response.SPACES + greetings + Duke_Response.SPACES);
    }

    //Goodbye by duke when invoked to shut down
    private static void dukeBye(){
        final String bye = "    Bye. Hope to see you again soon!\n";

        System.out.println(Duke_Response.SPACES + bye + Duke_Response.SPACES);
    }

    //List the task that were added into duke
    private static void dukeListTask(ArrayList<Task> tasks){
        System.out.print(Duke_Response.SPACES);
        System.out.println("    Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++){
            System.out.println("    " + (i+1) + "." + tasks.get(i).toString());
        }
        System.out.println(Duke_Response.SPACES);
    }

    //Add Task to be stored in duke
    private static void dukeAddTask(String message, ArrayList<Task> tasks){
        Task task;
        String[] arrStr = message.split(" ", 2);
        String[] splitStr;
        DateFormatter dateFormatter;

        try {
            if(arrStr.length == 1 || arrStr[1].isEmpty())
                throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! That is an invalid input\n" +
                        "    Please try again. \n" + Duke_Response.SPACES);
            switch (arrStr[0].toLowerCase()) {
                case "todo":
                    task = new Todo(arrStr[1]);
                    break;
                case "deadline":
                    if(!message.contains("/by "))
                        throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! You did not key in the deadline.\n" +
                                "    Please try again. \n" + Duke_Response.SPACES);
                    splitStr = arrStr[1].split("/by ", 2);
                    dateFormatter = new DateFormatter(splitStr[1]);
                    if(!dateFormatter.isValidDateTime())
                        throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! You did not key a valid deadline.\n" +
                                "    Please try again. \n" + Duke_Response.SPACES);

                    task = new Deadline(splitStr[0], dateFormatter.getDate() + dateFormatter.getTime());
                    break;
                case "event":
                    if(!message.contains("/at "))
                        throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! You did not key in the event time.\n" +
                                "    Please try again. \n" + Duke_Response.SPACES);
                    splitStr = arrStr[1].split("/at ", 2);
                    dateFormatter = new DateFormatter(splitStr[1]);
                    if(!dateFormatter.isValidDateTime())
                        throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! You did not key in a valid event time.\n" +
                                "    Please try again. \n" + Duke_Response.SPACES);

                    task = new Event(splitStr[0], dateFormatter.getDate() + dateFormatter.getTime());
                    break;
                default:
                    throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! You did not specify the type of task.\n" +
                            "    Please key in the task then description. \n" + Duke_Response.SPACES);
            }

            dukeWriteFile(message);

            tasks.add(task);
            System.out.println(Duke_Response.SPACES + "    Got it. I've added this task:\n"
                    + "     " + task.toString());
            System.out.println("    Now you have " + tasks.size() + " tasks in your list.\n"
                    + Duke_Response.SPACES);

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
                throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! You did not enter the task number.\n" +
                        "    To mark as done please key in a task number. \n" + Duke_Response.SPACES);
            taskNo = Integer.parseInt(arrStr[1]) - 1;

            if(tasks.size() <= taskNo)
                throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! That is an invalid task number.\n" +
                        "    To mark as done please key in a valid task number. \n" + Duke_Response.SPACES);

            Task task = tasks.get(taskNo);

            if(task.isCompleted()) {
                throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! This task has already been completed\n" + Duke_Response.SPACES);
            }

            task.markAsDone();
            dukeWriteFile(message);

            System.out.println(Duke_Response.SPACES + DONE + "      " + task.toString() + "\n" + Duke_Response.SPACES);

        } catch (NumberFormatException e){
            System.out.println(Duke_Response.SPACES + "    ☹ OOPS!!! You did not enter the task number.\n" +
                    "    To mark as done please key in a valid task number. \n" + Duke_Response.SPACES);
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }

    private static void dukeDeleteTask(String message, ArrayList<Task> tasks) {
        final String DELETE = "    Noted. I've removed this task:\n";
        int taskNo;
        String[] arrStr = message.split(" ");

        try {
            if (arrStr.length == 1)
                throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! You did not enter the task number.\n" +
                        "    To mark as done please key in a task number. \n" + Duke_Response.SPACES);
            taskNo = Integer.parseInt(arrStr[1]) - 1;

            if (tasks.size() <= taskNo)
                throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! That is an invalid task number.\n" +
                        "    To mark as done please key in a valid task number. \n" + Duke_Response.SPACES);

            dukeWriteFile(message);

            System.out.println(Duke_Response.SPACES + DELETE + "      " + tasks.get(taskNo).toString() + "\n" + Duke_Response.SPACES);

            tasks.remove(taskNo);

        } catch (NumberFormatException e) {
            System.out.println(Duke_Response.SPACES + "    ☹ OOPS!!! You did not enter the task number.\n" +
                    "    To mark as done please key in a valid task number. \n" + Duke_Response.SPACES);
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }

    private static void dukeFind(String message, ArrayList<Task> tasks) {
        final String FIND = "    Here are the matching tasks in your list:\n";
        ArrayList<Task> searchResult = new ArrayList<>();
        String[] arrStr = message.split(" ", 2);

        try {
            if(arrStr.length == 1 || arrStr[1].isEmpty())
                throw new DukeException(Duke_Response.SPACES + "    ☹ OOPS!!! That is an invalid input\n" +
                        "    Please try again. \n" + Duke_Response.SPACES);

            for (Task i : tasks) {
                if(i.getDescription().toLowerCase().contains(arrStr[1].toLowerCase())){
                    searchResult.add(i);
                }
            }

            if(searchResult.size() > 0){
                System.out.print(Duke_Response.SPACES + FIND);
                int counter = 1;
                for (Task i : searchResult) {
                    System.out.println("    " + counter + "." + i.toString());
                    counter++;
                }
                System.out.println(Duke_Response.SPACES);
            } else {
                System.out.println(Duke_Response.SPACES + "    ☹ OOPS!!! That is an invalid input\n" +
                        "    Please try again. \n" + Duke_Response.SPACES);
            }


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
                case "find":
                    dukeFind(message, tasks);
                    break;
                default:
                    dukeAddTask(message,tasks);
            }
        }
        scanner.close();
    }
}