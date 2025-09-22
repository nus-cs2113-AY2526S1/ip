package prime.exceptions;

/**
 * Represents a generic exception specific to the Prime task management system.
 * <p>
 * Serves as the base class for all custom exceptions in the application,
 * extending {@link Exception}.
 * </p>
 */
public class PrimeException extends Exception {

    /**
     * Constructs a new {@code PrimeException} with the specified detail message.
     *
     * @param message the detail message to describe the exception
     */
    public PrimeException(String message) {
        super(message);
    }
}
