import java.io.Serial;

abstract class AppStateException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    AppStateException(final String message) {
        super(message);
    }
}
