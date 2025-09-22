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

/**
 * Parses user input and generates the corresponding {@code Command} objects
 * for execution in the Prime Task Management system.
 * <p>
 * The {@code Parser} supports parsing of commands such as {@code todo},
 * {@code deadline}, {@code event}, {@code list}, {@code mark}, {@code unmark},
 * {@code delete}, {@code find}, and {@code bye}.
 * </p>
 */
public class Parser {
    private static final String BY = "/by";
    private static final String FROM = "/from";
    private static final String TO = "/to";

    /**
     * Parses the full user input and returns the corresponding {@code Command}.
     *
     * @param userInput The full input string entered by the user.
     * @return A {@code Command} object representing the parsed instruction.
     * @throws PrimeException If the input cannot be parsed into a valid command.
     */
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

    /**
     * Parses the arguments for a deadline task into description and deadline time.
     *
     * @param arguments The raw string containing the task description and {@code /by} token.
     * @return A string array where index 0 is the description and index 1 is the deadline,
     * or {@code null} if the {@code /by} token is missing.
     */
    public static String[] parseDeadline(String arguments) {
        int byIndex = arguments.indexOf(BY);
        if (byIndex == -1) {
            return null;
        }
        String description = arguments.substring(0, byIndex).trim();
        String by = arguments.substring(byIndex + BY.length()).trim();
        return new String[]{description, by};
    }

    /**
     * Parses the arguments for an event task into description, start time, and end time.
     *
     * @param arguments The raw string containing the task description and {@code /from} and {@code /to} tokens.
     * @return A string array where index 0 is the description, index 1 is the start time,
     * and index 2 is the end time, or {@code null} if either token is missing.
     */
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
