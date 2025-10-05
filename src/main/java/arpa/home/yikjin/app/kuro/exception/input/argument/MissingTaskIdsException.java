package arpa.home.yikjin.app.kuro.exception.input.argument;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppArgumentException;

/**
 * Missing task IDs exception
 */
public final class MissingTaskIdsException extends AppArgumentException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a missing task IDs exception
     */
    public MissingTaskIdsException() {
        super("task ids must be specified!");
    }
}
