package exception;

/**
 * Custom exception class for Twin application.
 * Used to signal user-facing errors or invalid operations.
 */
public class TwinException extends Exception {

    /**
     * Constructs a new TwinException with the specified error message.
     *
     * @param message the detail message for this exception
     */
    public TwinException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of this exception,
     * which is the error message.
     *
     * @return the detail message of this exception
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
