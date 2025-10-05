package arpa.home.yikjin.app.kuro.exception.io;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppIoException;

/**
 * Invalid file line exception
 */
public final class InvalidFileLineException extends AppIoException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create an invalid file line exception
     *
     * @param message Error message to show to the user
     */
    public InvalidFileLineException(final String message) {
        super(String.format("invalid file line: %s", message));
    }
}
