package prime.exceptions;

/**
 * Exception thrown when a user enters a command that is not recognized
 * by the Prime task management system.
 */
public class InvalidCommandException extends PrimeException {
    /**
     * Constructs a new {@code InvalidCommandException} with a default
     * detailed message explaining that the command is invalid.
     */
    public InvalidCommandException() {
        super("Invalid command Human! You are asking me to do stuff I have not been programmed to do");
    }
}
