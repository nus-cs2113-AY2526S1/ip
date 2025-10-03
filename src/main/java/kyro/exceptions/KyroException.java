package kyro.exceptions;

/**
 * Base exception type for the Kyro application.
 * <p>
 * All custom exceptions in Kyro extend from this class.
 * It provides a standard way to represent application-specific errors.
 */
public class KyroException extends Exception {

    public KyroException(String message) {
        super(message);
    }
}