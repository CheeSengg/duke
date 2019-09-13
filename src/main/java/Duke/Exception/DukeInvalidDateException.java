package Duke.Exception;

public class DukeInvalidDateException extends DukeException {
    public DukeInvalidDateException(){
        super("☹ OOPS!!! This is an invalid date format\n" +
                "Try \"dd/mm/yyyy hhmm\" instead.\n");
    }
}
