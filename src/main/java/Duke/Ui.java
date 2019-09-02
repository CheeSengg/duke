package Duke;

import Duke.Constant.Duke_Response;

import java.util.Scanner;

public class Ui {
    private String message;
    private Duke_Response dr = new Duke_Response();

    /**
     *  Constructor for Ui, Default value of message is greetings
     */
    public Ui(){
        this.message = dr.SPACES + dr.GREET + dr.SPACES;
    }

    /**
     * To read in the command by user to be processed
     * @return String input by user to be stored as command
     */
    public String readCommand(){
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    /**
     * Set messages after Command.execute is invoked
     * @param message The message that Duke will respond with after processing a command
     */
    public void setMessage(String message) {
        this.message = dr.SPACES + message + dr.SPACES;
    }

    /**
     * Print out the Duke LOGO
     */
    public void showWelcome(){
        System.out.println(dr.LOGO);
        new Ui().showLine();
    }

    /**
     * Print out the messages in the format that Duke will respond with
     */
    public void showLine(){
        System.out.println(this.message);
    }
}
