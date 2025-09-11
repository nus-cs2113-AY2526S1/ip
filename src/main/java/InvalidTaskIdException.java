import java.io.Serial;

final class InvalidTaskIdException extends AppArgumentException {
    @Serial
    private static final long serialVersionUID = 0L;

    InvalidTaskIdException(final int taskId) {
        super("task id " + taskId + " is invalid!");
    }

    InvalidTaskIdException(final String taskId) {
        super("task id '" + taskId + "' is invalid!");
    }
}
