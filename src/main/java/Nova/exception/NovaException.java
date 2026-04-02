package Nova.exception;

/**
 * Represents an exception specific to the Nova chatbot.
 * This is used to signal errors such as invalid commands,
 * invalid input formats, or task-related issues.
 */
public class NovaException extends Exception {

    /**
     * Creates a new {@code NovaException} with the specified message.
     *
     * @param message The detail message describing the error.
     */
    public NovaException(String message) {
        super(message);
    }
}
