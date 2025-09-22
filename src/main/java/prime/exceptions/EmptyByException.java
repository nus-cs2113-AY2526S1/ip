package prime.exceptions;

import prime.parser.CommandType;

/**
 * Exception thrown when the {@code /by} field of a command is empty.
 * <p>
 * Typically used for {@code Deadline} tasks where the deadline time is required
 * but not provided by the user.
 * </p>
 */
public class EmptyByException extends PrimeException {

    /**
     * Constructs a new {@code EmptyByException} with a detailed message
     * specifying the type of command that triggered the exception.
     *
     * @param commandType the type of command (e.g., Deadline) that caused the exception
     */
    public EmptyByException(CommandType commandType) {
        super("OOPS!!! The /by of " + commandType.toString() + " cannot be empty");
    }
}
