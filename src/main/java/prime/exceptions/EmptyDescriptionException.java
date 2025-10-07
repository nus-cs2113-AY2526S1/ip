package prime.exceptions;

import prime.parser.CommandType;

/**
 * Exception thrown when the description of a command is empty.
 * <p>
 * Typically used for tasks like {@code ToDo}, {@code Deadline}, or {@code Event}
 * when the user does not provide a description.
 * </p>
 */
public class EmptyDescriptionException extends PrimeException {

    /**
     * Constructs a new {@code EmptyDescriptionException} with a detailed message
     * specifying the type of command that caused the exception.
     *
     * @param commandType the type of command (e.g., ToDo, Deadline, Event) that triggered the exception
     */
    public EmptyDescriptionException(CommandType commandType) {
        super("OOPS!!! The description of " + commandType.toString() + " cannot be empty.");
    }
}
