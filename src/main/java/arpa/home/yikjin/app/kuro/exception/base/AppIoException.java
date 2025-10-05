package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

/**
 * Base Kuro input/output exception
 */
public abstract class AppIoException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a base Kuro input/output exception
     *
     * @param message Error message to show to the user
     */
    protected AppIoException(final String message) {
        super(message);
    }
}
