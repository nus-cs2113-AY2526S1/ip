package espresso.inputchecker;

import espresso.commands.Command;
import espresso.commands.CommandType;

/**
 * Provides validation logic for user input commands.
 * Ensures structural correctness and semantic integrity before command execution.
 */
public class Validator {

    /**
     * Validates commands that require a task index (e.g., mark, unmark, delete).
     * Checks for presence and numeric validity of the index.
     *
     * @param command   the type of command being validated
     * @param remainder the input string expected to contain the index
     * @return a {@code Command} marked VALID if the index is present and numeric;
     *         otherwise INVALID with an appropriate error message
     */
    public static Command validateIndex(CommandType command, String remainder) {
        if (remainder.trim().isEmpty()) {
            return new Command(CommandType.INVALID, Messages.INVALID_MISSING_INDEX);
        }
        try {
            Integer.parseInt(remainder.trim());
        } catch (NumberFormatException e) {
            return new Command(CommandType.INVALID, Messages.INVALID_INDEX);
        }
        return new Command(CommandType.VALID, remainder);
    }

    /**
     * Validates a todo command to ensure it includes a non-empty task name.
     *
     * @param remainder the input string expected to contain the task name
     * @return a {@code Command} marked VALID if the name is present;
     *         otherwise INVALID with an appropriate error message
     */
    public static Command validateTodoCommand(String remainder) {
        if (remainder.trim().isEmpty()) {
            return new Command(CommandType.INVALID, Messages.INVALID_TODO_NAME);
        }
        return new Command(CommandType.VALID, remainder);
    }

    /**
     * Validates an event command by checking the presence and order of "/from" and "/to" parameters.
     * Also ensures that the event name and time ranges are non-empty and correctly positioned.
     *
     * @param fromIndex index of the "/from" parameter in the input string
     * @param toIndex   index of the "/to" parameter in the input string
     * @param remainder the full input string after the "event" keyword
     * @return a {@code Command} marked VALID if all components are structurally correct;
     *         otherwise INVALID with a specific error message
     */
    public static Command validateEventIndexes(int fromIndex, int toIndex, String remainder) {
        if (fromIndex == -1) {
            return new Command(CommandType.INVALID, Messages.INVALID_EVENT_MISSING_FROM_PARAMETER);
        }
        if (toIndex == -1) {
            return new Command(CommandType.INVALID, Messages.INVALID_EVENT_MISSING_TO_PARAMETER);
        }
        if (fromIndex <= 0) {
            return new Command(CommandType.INVALID, Messages.INVALID_EVENT_NAME);
        }
        if (fromIndex > toIndex) {
            return new Command(CommandType.INVALID, Messages.INVALID_EVENT_WRONG_ORDER);
        }
        if (fromIndex + 6 >= toIndex) {
            return new Command(CommandType.INVALID, Messages.INVALID_EVENT_FROM);
        }
        if (toIndex + 4 >= remainder.trim().length()) {
            return new Command(CommandType.INVALID, Messages.INVALID_EVENT_TO);
        }
        return new Command(CommandType.VALID, remainder);
    }

    /**
     * Validates a deadline command by checking the presence and position of the "/by" parameter.
     * Ensures that both the task name and due date are present and correctly formatted.
     *
     * @param byIndex   index of the "/by" parameter in the input string
     * @param remainder the full input string after the "deadline" keyword
     * @return a {@code Command} marked VALID if all components are structurally correct;
     *         otherwise INVALID with a specific error message
     */
    public static Command validateDeadlineIndexes(int byIndex, String remainder) {
        if (byIndex == -1) {
            return new Command(CommandType.INVALID, Messages.INVALID_DEADLINE_MISSING_BY_PARAMETER);
        }
        if (byIndex <= 0) {
            return new Command(CommandType.INVALID, Messages.INVALID_DEADLINE_NAME);
        }
        if (byIndex + 4 >= remainder.trim().length()) {
            return new Command(CommandType.INVALID, Messages.INVALID_DEADLINE_BY);
        }
        return new Command(CommandType.VALID, remainder);
    }
}