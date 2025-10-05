package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

/**
 * Base Kuro app argument exception
 */
public abstract class AppArgumentException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a base Kuro app argument exception
     *
     * @param message Error message to show to user
     */
    protected AppArgumentException(final String message) {
        super(message);
    }
}
