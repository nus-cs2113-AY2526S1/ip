package arpa.home.yikjin.app.kuro.exception.io;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppIoException;

/**
 * Read file I/O exception
 */
public final class ReadFileIoException extends AppIoException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a file reading I/O exception
     *
     * @param message Error given by the system
     */
    public ReadFileIoException(final String message) {
        super(String.format("cannot read file: %s", message));
    }
}
