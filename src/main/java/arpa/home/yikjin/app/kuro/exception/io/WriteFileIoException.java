package arpa.home.yikjin.app.kuro.exception.io;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppIoException;

/**
 * Write file I/O exception
 */
public final class WriteFileIoException extends AppIoException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a file writing I/O exception
     *
     * @param message Error given by the system
     */
    public WriteFileIoException(final String message) {
        super(String.format("cannot write file: %s", message));
    }
}
