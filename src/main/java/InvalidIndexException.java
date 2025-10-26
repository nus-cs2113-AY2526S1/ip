/**
 * Represents an exception thrown when an invalid task index is provided.
 * An <code>InvalidIndexException</code> is used to indicate that the given
 * task number does not exist in the task list.
 */
public class InvalidIndexException extends DogeException {
    /**
     * Constructs a new InvalidIndexException with a fixed error message.
     */
    public InvalidIndexException() {
        super("BONK!! That task doesn't exist.");
    }
}
