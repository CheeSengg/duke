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
    private static Command parseEvent(String taskType, String command) throws DukeException{
        String[] splitStr = command.split("/at ", 2);
        if(splitStr.length == 1) throw new DukeException("Invalid Command\n");



        return new AddCommand(taskType, splitStr[0], splitStr[1]);
    }

    /**
     * Parse the date and return AddCommand class for Deadline task type
     * @param taskType The type of Task
     * @param command Command to be parse to description and dateTime
     * @return AddCommand for Deadline task type
     */
    private static Command parseDeadline(String taskType, String command) throws DukeException{
        String[] splitStr = command.split("/by ", 2);
        if(splitStr.length == 1) throw new DukeException("Invalid Command\n");

        return new AddCommand(taskType, splitStr[0], splitStr[1]);
    }

    /**
     * Mark the task indicated by user to be done
     * @param command The taskNo to be converted to an integer
     * @return DoneCommand with the index of item to be mark as done
     * @throws NumberFormatException if command has characters
     */
    private static Command parseDone(String command) throws NumberFormatException{
        int index = Integer.parseInt(command);

        return new DoneCommand(index);
    }

    /**
     * Delete the task indicated by user
     * @param command The taskNo to be converted to an integer
     * @return DeleteCommand with the index of item to be deleted
     * @throws NumberFormatException if command has characters.
     */
    private static Command parseDelete(String command) throws NumberFormatException{
        int index = Integer.parseInt(command);

        return new DeleteCommand(index);
    }

    /**
     * Return the correct command given by user, Class method
     * @param fullCommand Command input by user to be parse
     * @return The correct Command class as defined by first word
     * @throws NumberFormatException if command has characters.
     * @throws DukeException if the command is invalid or there are missing parameters.
     */
    public static Command parse(String fullCommand) throws NumberFormatException, DukeException{
        String[] splitStr = fullCommand.trim().split(" ", 2);

        switch(splitStr.length) {
            case 1:
                switch (splitStr[0].toLowerCase()) {
                    case "list":
                        return new ListCommand();
                    case "bye":
                        return new ByeCommand();
                    default:
                        throw new DukeException("Invalid Command\n");
                }
            case 2:
                if(!splitStr[1].isEmpty()) {
                    splitStr[1] = splitStr[1].trim();
                    switch (splitStr[0].toLowerCase()) {
                        case "todo":
                            return new AddCommand(splitStr[0], splitStr[1]);
                        case "deadline":
                            return parseDeadline(splitStr[0], splitStr[1]);
                        case "event":
                            return parseEvent(splitStr[0], splitStr[1]);
                        case "find":
                            return new FindCommand(splitStr[1]);
                        case "done":
                            return parseDone(splitStr[1]);
                        case "delete":
                            return parseDelete(splitStr[1]);
                        default:
                            throw new DukeException("Invalid Command\n");
                    }
                }
        }
        throw new DukeException("Invalid Command\n");
    }
}
