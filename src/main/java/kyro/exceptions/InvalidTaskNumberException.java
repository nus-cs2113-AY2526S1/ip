package kyro.exceptions;

/**
 * Exception thrown when the user specifies an invalid task number.
 * <p>
 * This error indicates that the provided index does not correspond
 * to any valid task in the current task list.
 */
public class InvalidTaskNumberException extends KyroException {

    public InvalidTaskNumberException() {
        super("Kyro thinks you need to learn how to count again...");
    }
}
