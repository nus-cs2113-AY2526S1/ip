import java.io.Serial;

final class MissingTaskIdsException extends AppArgumentException {
    @Serial
    private static final long serialVersionUID = 0L;

    MissingTaskIdsException() {
        super("task ids must be specified!");
    }
}
