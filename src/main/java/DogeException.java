/**
 * Represents a custom exception used for the Doge chatbot.
 * A <code>DogeException</code> is thrown whenever an error specific to the
 * program's logic occurs.
 */
public class DogeException extends Exception {
    /**
     * Constructs a new DogeException with a specified detail message.
     *
     * @param message The error message explaining what the error was.
     */
    public DogeException(String message) {
        super(message);
    }
}
