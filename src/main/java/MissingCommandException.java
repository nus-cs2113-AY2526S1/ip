import java.io.Serial;

final class MissingCommandException extends AppCommandException {
    @Serial
    private static final long serialVersionUID = 0L;

    MissingCommandException() {
        super("no command given!");
    }
}
