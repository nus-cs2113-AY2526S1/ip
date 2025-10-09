package parser;
import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnMarkCommand;
import exception.TwinException;
/**
 * The Parser class is responsible for interpreting raw user input strings
 * and converting them into corresponding {@link Command} objects.
 * <p>
 * It validates the syntax of user commands and throws a {@link TwinException}
 * when the command is invalid or missing arguments.
 */
public class Parser {

    /**
     * Parses a user input string into a {@link Command} object.
     *
     * @param userText the raw input string entered by the user
     * @return the corresponding {@link Command} object for execution
     * @throws TwinException if the input is invalid, unknown, or missing arguments
     */
    public static Command parse(String userText) throws TwinException {
        if (userText == null || userText.trim().isEmpty()) {
            throw new TwinException("Please enter a valid input! Input cannot be blank!");
        }

        String trimmed = userText.trim();
        // split into command and the rest (arguments)
        String[] parts = trimmed.split(" ", 2);
        String command = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
        case "list":
            return new ListCommand();

        case "mark":
            if (args.isEmpty()) {
                throw new TwinException("Please add what item number to mark.");
            }
            try {
                int listIndex = Integer.parseInt(args);
                return new MarkCommand(listIndex);
            } catch (NumberFormatException e) {
                throw new TwinException("Please give me a valid number to mark!");
            }

        case "unmark":
            if (args.isEmpty()) {
                throw new TwinException("Please add what item number to unmark.");
            }
            try {
                int listIndex = Integer.parseInt(args);
                return new UnMarkCommand(listIndex);
            } catch (NumberFormatException e) {
                throw new TwinException("Please give me a valid number to unmark!");
            }

        case "todo":
            if (args.isEmpty()) {
                throw new TwinException("The description of a todo cannot be empty.");
            }
            return new AddTodoCommand(args);

        case "deadline":
            // require " by " and use limit 2 so description can contain "by"
            String[] deadlineParameters = trimmed.split(" by ", 2);
            if (deadlineParameters.length < 2) {
                throw new TwinException("Usage: deadline <description> by <time>");
            }
            String deadlineDescription = deadlineParameters[0].substring("deadline".length()).trim();
            String by = deadlineParameters[1].trim();
            if (deadlineDescription.isEmpty() || by.isEmpty()) {
                throw new TwinException("Usage: deadline <description> by <time>");
            }
            return new AddDeadlineCommand(deadlineDescription, by);

        case "event":
            String[] eventParameters = trimmed.split(" from ", 2);
            if (eventParameters.length < 2) {
                throw new TwinException("Usage: event <description> from <start> to <end>");
            }
            String eDesc = eventParameters[0].substring("event".length()).trim();
            String[] times = eventParameters[1].split(" to ", 2);
            if (eDesc.isEmpty() || times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                throw new TwinException("Usage: event <description> from <start> to <end>");
            }
            return new AddEventCommand(eDesc, times[0].trim(), times[1].trim());

        case "delete":
            if (args.isEmpty()) {
                throw new TwinException("Please provide the task number to delete.");
            }
            try {
                int listIndex = Integer.parseInt(args);
                return new DeleteCommand(listIndex);
            } catch (NumberFormatException e) {
                throw new TwinException("Please give me a valid number to delete!");
            }

        case "bye":
            Command c = new ExitCommand();
            return c;

        case "find":
            if (args.isEmpty()) {
                throw new TwinException("Please provide a keyword to find!");
            }
            return new FindCommand(args);

        default:
            throw new TwinException("Unknown command: " + command);
        }
    }
}
