package exceptions;

/**
 * Custom exception class for the Hero application
 * This exception is thrown when an error specific to the application's logic occurs
 * providing a way to handle application-level errors gracefully
 */
public class HeroException extends Exception {

    /**
     * Constructs a HeroException with the specified error message
     *
     * @param message The detail message of the exception
     */
    public HeroException(String message) {
        super(message);
    }
}
