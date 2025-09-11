package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

public abstract class AppStateException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    protected AppStateException(final String message) {
        super(message);
    }
}
