package arpa.home.yikjin.app.kuro.exception.input.argument;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppArgumentException;

/**
 * Missing task name exception
 */
public final class MissingTaskNameException extends AppArgumentException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a missing task name exception
     *
     * @param taskType Type of task that is missing the task name
     */
    public MissingTaskNameException(final String taskType) {
        super(taskType + " name cannot be empty!");
    }
}
