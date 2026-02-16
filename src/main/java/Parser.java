import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.PepException;

/**
 * Parses raw user input strings and returns the corresponding Command object.
 * Supports commands such as list, mark, unmark, delete, todo, deadline, event, and find.
 */
public class Parser {

    /**
     * Parses the given user input and returns the appropriate Command.
     *
     * @param userInput the full line of input entered by the user
     * @return a Command object representing the user's request
     * @throws PepException if the input is invalid or unrecognized
     */
    public static Command parse(String userInput) throws PepException {
        if (userInput.equals("bye")) {
            return new ExitCommand();

        } else if (userInput.equals("list")) {
            return new ListCommand();

        } else if (userInput.startsWith("mark ")) {
            int index = parseIndex(userInput, "mark <task number>");
            return new MarkCommand(index);

        } else if (userInput.startsWith("unmark ")) {
            int index = parseIndex(userInput, "unmark <task number>");
            return new UnmarkCommand(index);

        } else if (userInput.startsWith("delete ")) {
            int index = parseIndex(userInput, "delete <task number>");
            return new DeleteCommand(index);

        } else if (userInput.startsWith("todo ")) {
            return new AddTodoCommand(userInput.substring(5).trim());

        } else if (userInput.startsWith("deadline ")) {
            String[] parts = userInput.substring(9).split(" /by ", 2);
            if (parts.length < 2) {
                throw new PepException("Usage: deadline <description> /by <yyyy-MM-dd>");
            }
            return new AddDeadlineCommand(parts[0].trim(), parts[1].trim());

        } else if (userInput.startsWith("event ")) {
            String[] parts = userInput.substring(6).split(" /from ", 2);
            if (parts.length < 2) {
                throw new PepException("Usage: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
            }
            String[] timeParts = parts[1].split(" /to ", 2);
            if (timeParts.length < 2) {
                throw new PepException("Usage: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
            }
            return new AddEventCommand(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());

        } else if (userInput.startsWith("find ")) {
            String keyword = userInput.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new PepException("Usage: find <keyword>");
            }
            return new FindCommand(keyword);
        }

        throw new PepException("Unknown command: " + userInput);
    }

    /**
     * Helper to parse a numeric index argument safely.
     *
     * @param userInput the full user input string
     * @param usage     the usage string to show in case of error
     * @return the zero-based index parsed from the input
     * @throws PepException if the argument is missing or not a valid integer
     */
    private static int parseIndex(String userInput, String usage) throws PepException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            throw new PepException("Usage: " + usage);
        }
        try {
            int index = Integer.parseInt(parts[1].trim()) - 1;
            if (index < 0) {
                throw new PepException("Task number must be positive.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new PepException("Task number must be a valid integer.");
        }
    }
}
