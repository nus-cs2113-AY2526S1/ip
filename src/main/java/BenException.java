/**
 * Represents an exception specific to the Ben application.
 * Used to indicate user input errors or invalid operations
 * without crashing the program.
 */
public class BenException extends Exception {

    /**
     * Constructs a BenException with the specified detail message.
     *
     * @param message The detail message describing the cause of the error.
     */
    public BenException(String message) {
        super(message);
    }
}