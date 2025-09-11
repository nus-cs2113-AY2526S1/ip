package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

public abstract class AppArgumentException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    protected AppArgumentException(final String message) {
        super(message);
    }
}
