package prime.exceptions;

/**
 * Exception thrown when a user provides an invalid task number.
 * <p>
 * The invalid task number is either less than 1 or greater than the
 * maximum number of tasks allowed in the task list.
 * </p>
 */
public class InvalidTaskNumberException extends PrimeException {

    /**
     * Constructs a new {@code InvalidTaskNumberException} with a detailed message
     * specifying the invalid task number and the allowed range.
     *
     * @param taskNo the invalid task number provided by the user
     * @param max    the maximum number of tasks allowed in the task list
     */
    public InvalidTaskNumberException(int taskNo, int max) {
        super("Invalid task number of " + taskNo + "! Please try again." +
                "\n    You are allowed to enter a number between 1 and " + max);
    }
}
