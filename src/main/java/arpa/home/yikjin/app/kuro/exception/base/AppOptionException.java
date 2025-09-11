package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

public abstract class AppOptionException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    protected AppOptionException(final String message) {
        super(message);
    }
}
