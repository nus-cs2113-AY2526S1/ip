package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

public abstract class AppIoException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    protected AppIoException(final String message) {
        super(message);
    }
}
