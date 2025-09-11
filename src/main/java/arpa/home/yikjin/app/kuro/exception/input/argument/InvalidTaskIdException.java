package arpa.home.yikjin.app.kuro.exception.input.argument;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppArgumentException;

public final class InvalidTaskIdException extends AppArgumentException {
    @Serial
    private static final long serialVersionUID = 0L;

    public InvalidTaskIdException(final int taskId) {
        super("task id " + taskId + " is invalid!");
    }

    public InvalidTaskIdException(final String taskId) {
        super("task id '" + taskId + "' is invalid!");
    }
}
