package prime.exceptions;

import prime.parser.CommandType;

/**
 * Exception thrown when the {@code /from} field of a command is empty.
 * <p>
 * Typically used for {@code Event} tasks where the start time is required
 * but not provided by the user.
 * </p>
 */
public class EmptyFromException extends PrimeException {

    /**
     * Constructs a new {@code EmptyFromException} with a detailed message
     * specifying the type of command that triggered the exception.
     *
     * @param commandType the type of command (e.g., Event) that caused the exception
     */
    public EmptyFromException(CommandType commandType) {
        super("OOPS!!! The /from of " + commandType.toString() + " cannot be empty");
    }
}
