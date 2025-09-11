package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

public abstract class AppCommandException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    protected AppCommandException(final String message) {
        super(message);
    }
}
