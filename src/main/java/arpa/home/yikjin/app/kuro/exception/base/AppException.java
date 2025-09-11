package arpa.home.yikjin.app.kuro.exception.base;

import java.io.Serial;

public abstract class AppException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 0L;

    AppException(final String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "error: " + getLocalizedMessage();
    }
}
