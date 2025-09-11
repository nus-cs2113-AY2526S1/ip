import java.io.Serial;

abstract class AppArgumentException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    AppArgumentException(final String message) {
        super(message);
    }
}
