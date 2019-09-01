package Duke.Parser;

import Duke.Commands.*;
import Duke.Exception.DukeException;

public class Parser {

    /**
     * Parse the date and return AddCommand class for Event task type
     * @param taskType The type of Task
     * @param command Command to be parse to description and dateTime
     * @return AddCommand for Event task type
     */
    private static Command parseEvent(String taskType, String command){
        String[] splitStr = command.split("/at ", 2);

        return new AddCommand(taskType, splitStr[0], splitStr[1]);
    }

    /**
     * Parse the date and return AddCommand class for Deadline task type
     * @param taskType The type of Task
     * @param command Command to be parse to description and dateTime
     * @return AddCommand for Deadline task type
     */
    private static Command parseDeadline(String taskType, String command){
        String[] splitStr = command.split("/by ", 2);

        return new AddCommand(taskType, splitStr[0], splitStr[1]);
    }

    /**
     * Mark the task indicated by user to be done
     * @param command The taskNo to be converted to an integer
     * @return DoneCommand with the index of item to be mark as done
     * @throws NumberFormatException
     */
    private static Command parseDone(String command) throws NumberFormatException{
        int index = Integer.parseInt(command);

        return new DoneCommand(index);
    }

    /**
     * Delete the task indicated by user
     * @param command The taskNo to be converted to an integer
     * @return DeleteCommand with the index of item to be deleted
     * @throws NumberFormatException
     */
    private static Command parseDelete(String command) throws NumberFormatException{
        int index = Integer.parseInt(command);

        return new DeleteCommand(index);
    }

    /**
     * To return the correct command given by user, Class method
     * @param fullCommand Command input by user to be parse
     * @return The correct Command class as defined by first word
     * @throws NumberFormatException
     * @throws DukeException
     */
    public static Command parse(String fullCommand) throws NumberFormatException, DukeException{
        String[] splitStr = fullCommand.split(" ", 2);

        switch (splitStr[0].toLowerCase()){
            case "todo":
                return new AddCommand(splitStr[0], splitStr[1]);
            case "deadline":
                return parseDeadline(splitStr[0], splitStr[1]);
            case "event":
                return parseEvent(splitStr[0], splitStr[1]);
            case "list":
                return new ListCommand();
            case "find":
                return new FindCommand(splitStr[1]);
            case "done":
                return parseDone(splitStr[1]);
            case "delete":
                return parseDelete(splitStr[1]);
            case "bye":
                return new ByeCommand();
            default:
                throw new DukeException("Invalid Command");
        }


    }
}
