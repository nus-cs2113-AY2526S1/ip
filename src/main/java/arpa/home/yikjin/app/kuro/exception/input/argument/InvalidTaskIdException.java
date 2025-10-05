package arpa.home.yikjin.app.kuro.exception.input.argument;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppArgumentException;

/**
 * Invalid task ID exception
 */
public final class InvalidTaskIdException extends AppArgumentException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create an invalid task ID exception
     *
     * @param taskId Invalid task ID given
     */
    public InvalidTaskIdException(final int taskId) {
        super("task id " + taskId + " is invalid!");
    }

    /**
     * Create an invalid task ID exception
     *
     * @param taskId Invalid task ID given
     */
    public InvalidTaskIdException(final String taskId) {
        super("task id '" + taskId + "' is invalid!");
    }
}
