package arpa.home.yikjin.app.kuro.exception.io;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppIoException;

public final class InvalidFileLineException extends AppIoException {
    @Serial
    private static final long serialVersionUID = 0L;

    public InvalidFileLineException(final String message) {
        super(String.format("invalid file line: %s", message));
    }
}
