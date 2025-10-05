package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

/**
 * Base Kuro option exception
 */
public abstract class AppOptionException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a base Kuro option exception
     *
     * @param message Error message to show to the user
     */
    protected AppOptionException(final String message) {
        super(message);
    }
}
