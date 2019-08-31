package Duke.Parser;

import Duke.Commands.*;
import Duke.Exception.DukeException;

public class Parser {

    private static Command parseEvent(String taskType, String command){
        String[] splitStr = command.split("/at ", 2);

        return new AddCommand(taskType, splitStr[0], splitStr[1]);
    }

    private static Command parseDeadline(String taskType, String command){
        String[] splitStr = command.split("/by ", 2);

        return new AddCommand(taskType, splitStr[0], splitStr[1]);
    }

    private static Command parseDone(String command) throws NumberFormatException{
        int index = Integer.parseInt(command);

        return new DoneCommand(index);
    }

    private static Command parseDelete(String command) throws NumberFormatException{
        int index = Integer.parseInt(command);

        return new DeleteCommand(index);
    }

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
                return null;
            default:
                throw new DukeException("Invalid Command");
        }


    }
}
