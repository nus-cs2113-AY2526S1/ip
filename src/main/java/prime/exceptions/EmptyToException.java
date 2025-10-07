package prime.exceptions;

import prime.parser.CommandType;

/**
 * Exception thrown when the {@code /to} field of a command is empty.
 * <p>
 * Typically used for {@code Event} tasks where the end time is required
 * but not provided by the user.
 * </p>
 */
public class EmptyToException extends PrimeException {

    /**
     * Constructs a new {@code EmptyToException} with a detailed message
     * specifying the type of command that triggered the exception.
     *
     * @param commandType the type of command (e.g., Event) that caused the exception
     */
    public EmptyToException(CommandType commandType) {
        super("OOPS!!! The /to of " + commandType.toString() + " cannot be empty");
    }
}
