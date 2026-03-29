package michael.Exception;

/**
 * Exception thrown when a number provided by the user is out of the valid range.
 * Used to signal invalid task indices or similar errors.
 */
public class NumberRangeException extends Exception {
    /**
     * Returns a message indicating the number is not within the valid range.
     *
     * @return The error message for out-of-range numbers
     */
    public String notInRangeMessage() {
        return "Oh no! the number you have given is not within range, please try again";
    }
}
