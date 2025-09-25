package espresso.inputchecker;

import espresso.commands.Command;
import espresso.commands.CommandType;

/**
 * Parses user input strings into structured {@code Command} objects.
 * Supports task creation, modification, deletion, and search operations.
 */
public class Parser {

    /**
     * Parses raw user input into a {@code Command} object.
     * Determines the command type based on the first keyword and delegates to specialized parsers.
     *
     * @param userInput the full input string entered by the user
     * @return a {@code Command} object representing the parsed action
     */
    public static Command parseInput(String userInput) {
        int spaceIndex = userInput.indexOf(" ");
        String[] arguments = userInput.split(" ");
        String keyword = arguments[0];
        String remainder = "";

        if (arguments.length > 1) {
            remainder = userInput.substring(spaceIndex + 1);
        }

        return switch (keyword) {
            case "list" -> new Command(CommandType.LIST, remainder);
            case "mark" -> parseIndexCommand(CommandType.MARK, remainder);
            case "unmark" -> parseIndexCommand(CommandType.UNMARK, remainder);
            case "delete" -> parseIndexCommand(CommandType.DELETE, remainder);
            case "todo" -> parseTodoCommand(remainder);
            case "deadline" -> parseDeadlineCommand(remainder);
            case "event" -> parseEventCommand(remainder);
            case "find" -> parseFindCommand(remainder);
            case "help" -> new Command(CommandType.HELP, "");
            default -> new Command(CommandType.INVALID, Messages.INVALID_KEYWORD);
        };
    }

    /**
     * Parses a todo command and validates its structure.
     *
     * @param remainder the portion of input after the "todo" keyword
     * @return a valid {@code Command} or an invalid one with error message
     */
    private static Command parseTodoCommand(String remainder) {
        Command invalidCommand = Validator.validateTodoCommand(remainder);
        if (invalidCommand.isInvalid()) {
            return invalidCommand;
        }
        return new Command(CommandType.TODO, remainder);
    }

    /**
     * Parses commands that operate on a task index (e.g., mark, unmark, delete).
     * Validates the index and sets it in the command.
     *
     * @param type      the command type (MARK, UNMARK, DELETE)
     * @param remainder the portion of input containing the index
     * @return a valid {@code Command} with task index, or an invalid one with error message
     */
    private static Command parseIndexCommand(CommandType type, String remainder) {
        Command invalidCommand = Validator.validateIndex(type, remainder);
        if (invalidCommand.isInvalid()) {
            return invalidCommand;
        }
        int index = Integer.parseInt(remainder.trim());

        Command cmd = new Command(type, "");
        cmd.setTaskIndex(index - 1);
        return cmd;
    }

    /**
     * Parses a deadline command and extracts description and due date.
     * Validates the presence and position of the "/by" parameter.
     *
     * @param remainder the portion of input after the "deadline" keyword
     * @return a valid {@code Command} with deadline info, or an invalid one with error message
     */
    private static Command parseDeadlineCommand(String remainder) {
        int byIndex = remainder.indexOf("/by");

        Command invalidCommand = Validator.validateDeadlineIndexes(byIndex, remainder);
        if (invalidCommand.isInvalid()) {
            return invalidCommand;
        }

        String description = remainder.substring(0, byIndex).trim();
        String deadline = remainder.substring(byIndex + 4).trim();

        Command cmd = new Command(CommandType.DEADLINE, description);
        cmd.setDeadline(deadline);
        return cmd;
    }

    /**
     * Parses an event command and extracts description, start time, and end time.
     * Validates the presence and order of "/from" and "/to" parameters.
     *
     * @param remainder the portion of input after the "event" keyword
     * @return a valid {@code Command} with event timing, or an invalid one with error message
     */
    private static Command parseEventCommand(String remainder) {
        int fromIndex = remainder.indexOf("/from");
        int toIndex = remainder.indexOf("/to");

        Command invalidCommand = Validator.validateEventIndexes(fromIndex, toIndex, remainder);
        if (invalidCommand.isInvalid()) {
            return invalidCommand;
        }

        String description = remainder.substring(0, fromIndex).trim();
        String from = remainder.substring(fromIndex + 6, toIndex).trim();
        String to = remainder.substring(toIndex + 4);

        Command cmd = new Command(CommandType.EVENT, description);
        cmd.setEventStart(from);
        cmd.setEventEnd(to);
        return cmd;
    }

    /**
     * Parses a find command and extracts search keywords.
     * Validates that at least one keyword is present.
     *
     * @param remainder the portion of input after the "find" keyword
     * @return a valid {@code Command} with keywords, or an invalid one with error message
     */
    private static Command parseFindCommand(String remainder) {
        Command cmd = new Command(CommandType.FIND, remainder);
        String[] keywords = remainder.strip().split(" ");
        if (keywords.length == 0) {
            return new Command(CommandType.INVALID, Messages.INVALID_FIND_MISSING_KEYWORD);
        }
        cmd.setKeywords(keywords);
        return cmd;
    }
}
