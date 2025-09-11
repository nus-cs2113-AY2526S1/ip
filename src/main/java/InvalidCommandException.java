import java.io.Serial;

final class InvalidCommandException extends AppCommandException {
    @Serial
    private static final long serialVersionUID = 0L;

    InvalidCommandException(final String command) {
        super("unknown command '" + command + "'");
    }
}
