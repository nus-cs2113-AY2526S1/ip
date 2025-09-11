import java.io.Serial;

final class MissingTaskNameException extends AppArgumentException {
    @Serial
    private static final long serialVersionUID = 0L;

    MissingTaskNameException(final String taskType) {
        super(taskType + " name cannot be empty!");
    }
}
