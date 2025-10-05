package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

/**
 * Base Kuro command exception
 */
public abstract class AppCommandException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a base Kuro command exception
     *
     * @param message Error message to show to the user
     */
    protected AppCommandException(final String message) {
        super(message);
    }
}
