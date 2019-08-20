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

}
