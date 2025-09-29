package parser;

import exceptions.HeroException;

/**
 * Handles the parsing of user input for the Hero application
 * This class validates commands and extracts the necessary information to be executed
 */
public class Parser {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_FIND = "find";

    public static final String DELIMITER_SPACE = " ";
    public static final String DELIMITER_BY = "/by";
    public static final String DELIMITER_FROM = "/from";
    public static final String DELIMITER_TO = "/to";

    /**
     * Parses the user's input string and returns a formatted command and its arguments
     *
     * @param input The user's input string
     * @return A String array containing the command and its arguments
     * @throws HeroException If the input is empty or the command is unknown
     */
    public static String[] parseInput(String input) throws HeroException {
        if (input == null || input.trim().isEmpty()) {
            throw new HeroException("Input cannot be empty!");
        }

        String trimmedInput = input.trim();

        String[] words = trimmedInput.split(DELIMITER_SPACE, 2);
        String command = words[0].toLowerCase();
        String content = words.length > 1 ? words[1] : "";

        switch (command) {
        case COMMAND_BYE:
        case COMMAND_LIST:
            return new String[]{command};
        case COMMAND_MARK:
        case COMMAND_UNMARK:
        case COMMAND_DELETE:
            return parseNumberedCommand(content, command);
        case COMMAND_FIND:
            return parseFindCommand(content, command);
        case COMMAND_TODO:
            return parseToDoCommand(content, command);
        case COMMAND_DEADLINE:
            return parseDeadlineCommand(content, command);
        case COMMAND_EVENT:
            return parseEventCommand(content, command);
        default:
            throw new HeroException("Unknown command!");
        }
    }

    /**
     * Parses commands that require a task number (mark, unmark, delete).
     *
     * @param command The command string.
     * @param content The content following the command.
     * @return A String array with the command and the task number.
     * @throws HeroException If the content is empty or not a valid number.
     */
    private static String[] parseNumberedCommand(String content, String command) throws HeroException {
        // Check that the task number is not empty
        if (content.isEmpty()) {
            throw new HeroException("The " + command + " command must be followed by a task number.");
        }
        try {
            Integer.parseInt(content);
        } catch (NumberFormatException e) {
            // Check that string is a valid integer
            throw new HeroException("The " + command + " command must be followed by a valid number!");
        }

        return new String[]{command, content};
    }

    /**
     * Parses the find command.
     *
     * @param command The command string.
     * @param content The content following the command.
     * @return A String array with the command and the find text.
     * @throws HeroException If the content is empty.
     */
    private static String[] parseFindCommand(String content, String command) throws HeroException {
        // Check if find text is not empty
        if (content.isEmpty()) {
            throw new HeroException("What are you finding?");
        }

        return new String[]{command, content};
    }

    /**
     * Parses the to-do command.
     *
     * @param command The command string.
     * @param content The content following the command.
     * @return A String array with the command and the todo description.
     * @throws HeroException If the content is empty.
     */
    private static String[] parseToDoCommand(String content, String command) throws HeroException {
        // Check if to do description is not empty
        if (content.isEmpty()) {
            throw new HeroException("The description of the to do task cannot be empty!");
        }

        return new String[]{command, content};
    }

    /**
     * Parses the deadline command.
     *
     * @param command The command string.
     * @param content The content following the command.
     * @return A String array with the command, description, and deadline.
     * @throws HeroException If the content is missing the delimiter or required parts.
     */
    private static String[] parseDeadlineCommand(String content, String command) throws HeroException {
        // Check if deadline description is empty
        if (content.isEmpty()) {
            throw new HeroException("The description of the deadline task cannot be empty!");
        }

        // Check if string has "/by" delimiter
        if (!content.contains(DELIMITER_BY)) {
            throw new HeroException("The deadline task must have a /by delimiter!");
        }

        // Extract the deadline description and deadline
        String[] deadlineParts = content.split(DELIMITER_BY, 2);
        String deadlineDescription = deadlineParts.length > 0 ? deadlineParts[0].trim() : "";

        // Check if deadline description is not empty
        if (deadlineDescription.isEmpty()) {
            throw new HeroException("The description of the deadline task cannot be empty!");
        }
        String deadlineDeadline = deadlineParts.length > 1 ? deadlineParts[1].trim() : "";

        // Check if deadline is not empty
        if (deadlineDeadline.isEmpty()) {
            throw new HeroException("The deadline of the deadline task cannot be empty!");
        }

        return new String[]{command, deadlineDescription, deadlineDeadline};
    }

    /**
     * Parses the event command.
     *
     * @param command The command string.
     * @param content The content following the command.
     * @return A String array with the command, description, from time, and to time.
     * @throws HeroException If the content is missing delimiters or required parts.
     */
    private static String[] parseEventCommand(String content, String command) throws HeroException {
        // Check if event description is not empty
        if (content.isEmpty()) {
            throw new HeroException("The description of the event task cannot be empty!");
        }

        // Check if string has "/from" delimiter
        if (!content.contains(DELIMITER_FROM)) {
            throw new HeroException("The event task must have a /from delimiter!");
        }

        // Check if event has "/to" delimiter
        if (!content.contains(DELIMITER_TO)) {
            throw new HeroException("The event task must have a /to delimiter!");
        }

        // Extract the event description
        String[] eventParts1 = content.split(DELIMITER_FROM, 2);
        String eventDescription = eventParts1.length > 0 ? eventParts1[0].trim() : "";

        // Check if event description not empty
        if (eventDescription.isEmpty()) {
            throw new HeroException("The description of the event task cannot be empty!");
        }

        // Extract the start period and end period
        String[] eventParts2 = eventParts1[1].split(DELIMITER_TO, 2);
        String eventFrom = eventParts2.length > 0 ? eventParts2[0].trim() : "";

        // Check if the start period is not empty
        if (eventFrom.isEmpty()) {
            throw new HeroException("The event task start period cannot be empty!");
        }
        String eventTo = eventParts2.length > 1 ? eventParts2[1].trim() : "";

        // Check if the end period is empty
        if (eventTo.isEmpty()) {
            throw new HeroException("The event task end period cannot be empty!");
        }

        return new String[]{command, eventDescription, eventFrom, eventTo};
    }
}
