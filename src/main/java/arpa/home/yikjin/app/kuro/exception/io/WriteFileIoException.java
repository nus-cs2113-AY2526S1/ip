package arpa.home.yikjin.app.kuro.exception.io;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppIoException;

public final class WriteFileIoException extends AppIoException {
    @Serial
    private static final long serialVersionUID = 0L;

    public WriteFileIoException(final String message) {
        super(String.format("cannot write file: %s", message));
    }
}
