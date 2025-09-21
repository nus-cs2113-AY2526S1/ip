package prime.parser;

import prime.command.ByeCommand;
import prime.command.Command;
import prime.command.DeadlineCommand;
import prime.command.DeleteCommand;
import prime.command.EventCommand;
import prime.command.FindCommand;
import prime.command.InvalidCommand;
import prime.command.ListCommand;
import prime.command.MarkCommand;
import prime.command.TodoCommand;
import prime.command.UnmarkCommand;
import prime.exceptions.PrimeException;

public class Parser {
    private static final String BY = "/by";
    private static final String FROM = "/from";
    private static final String TO = "/to";

    public static Command parse(String userInput) throws PrimeException {
        // Split out the command word and the arguments using the Regex for space
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        String arguments;
        if (parts.length > 1) {
            arguments = parts[1];
        } else {
            arguments = "";
        }

        // Inspired from CG2111A mod
        switch (commandWord) {
        case "bye":
            return new ByeCommand(arguments);
        case "list":
            return new ListCommand(arguments);
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "todo":
            return new TodoCommand(arguments);
        case "deadline":
            return new DeadlineCommand(arguments);
        case "event":
            return new EventCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        default:
            return new InvalidCommand(arguments);
        }
    }

    // Helps me extract information for description and by and stores it in an array
    public static String[] parseDeadline(String arguments) {
        int byIndex = arguments.indexOf(BY);
        if (byIndex == -1) {
            return null; // no by
        }
        String description = arguments.substring(0, byIndex).trim();
        String by = arguments.substring(byIndex + BY.length()).trim();
        return new String[]{description, by};
    }

    // Helps me extract information for description, from and to and stores it in an array
    public static String[] parseEvent(String arguments) {
        int fromIndex = arguments.indexOf(FROM);
        int toIndex = arguments.indexOf(TO);
        if (fromIndex == -1 || toIndex == -1) {
            return null;
        }
        String description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + FROM.length(), toIndex).trim();
        String to = arguments.substring(toIndex + TO.length()).trim();
        return new String[]{description, from, to};
    }
}
