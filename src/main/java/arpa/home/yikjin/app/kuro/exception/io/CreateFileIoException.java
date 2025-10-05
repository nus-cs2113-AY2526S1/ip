package arpa.home.yikjin.app.kuro.exception.io;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppIoException;

/**
 * Create file I/O exception
 */
public final class CreateFileIoException extends AppIoException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a file creation I/O exception
     *
     * @param message Error given by the system
     */
    public CreateFileIoException(final String message) {
        super(String.format("cannot create file: %s", message));
    }
}
