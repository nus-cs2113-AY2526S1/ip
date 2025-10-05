package arpa.home.yikjin.app.kuro.exception.state;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppStateException;

/**
 * Empty task list exception
 */
public final class EmptyTaskListException extends AppStateException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create an empty task list exception
     */
    public EmptyTaskListException() {
        super("no tasks to show!");
    }
}
