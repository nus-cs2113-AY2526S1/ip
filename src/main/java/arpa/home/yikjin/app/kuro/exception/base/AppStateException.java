package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

/**
 * Base Kuro state exception
 */
public abstract class AppStateException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a base Kuro state exception
     *
     * @param message Error message to show to the user
     */
    protected AppStateException(final String message) {
        super(message);
    }
}
