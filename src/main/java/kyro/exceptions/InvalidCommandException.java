package kyro.exceptions;

/**
 * Exception thrown when the user inputs an unrecognized command.
 * <p>
 * This error indicates that the command entered does not match
 * any valid command in the Kyro application.
 */
public class InvalidCommandException extends KyroException {

    public InvalidCommandException() {
        super("Kyro has no clue what you're saying...");
    }
}
