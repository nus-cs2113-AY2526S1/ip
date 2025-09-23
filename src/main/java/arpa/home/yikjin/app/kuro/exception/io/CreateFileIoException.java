package arpa.home.yikjin.app.kuro.exception.io;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppIoException;

public final class CreateFileIoException extends AppIoException {
    @Serial
    private static final long serialVersionUID = 0L;

    public CreateFileIoException(final String message) {
        super(String.format("cannot create file: %s", message));
    }
}
