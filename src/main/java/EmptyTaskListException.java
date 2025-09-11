import java.io.Serial;

final class EmptyTaskListException extends AppStateException {
    @Serial
    private static final long serialVersionUID = 0L;

    EmptyTaskListException() {
        super("no tasks to show!");
    }
}
