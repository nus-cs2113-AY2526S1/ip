import java.io.Serial;

abstract class AppCommandException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    AppCommandException(final String message) {
        super(message);
    }
}
