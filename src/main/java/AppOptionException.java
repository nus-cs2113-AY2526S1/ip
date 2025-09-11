import java.io.Serial;

abstract class AppOptionException extends AppException {
    @Serial
    private static final long serialVersionUID = 0L;

    AppOptionException(final String message) {
        super(message);
    }
}
