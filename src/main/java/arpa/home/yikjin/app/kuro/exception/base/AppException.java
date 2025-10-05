package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

/**
 * Base Kuro runtime exception
 */
public abstract class AppException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a base Kuro runtime exception
     *
     * @param message Message to show to user
     */
    AppException(final String message) {
        super(message);
    }

    /**
     * Format the error message to display to the user
     *
     * @return Formatted error message
     */
    @Override
    public String toString() {
        return "error: " + getLocalizedMessage();
    }
}
