package arpa.home.yikjin.app.kuro.exception.input.argument;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppArgumentException;

/**
 * Excess task IDs exception
 */
public class ExcessTaskIdsException extends AppArgumentException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create an excess task IDs exception
     */
    public ExcessTaskIdsException() {
        super("too many task ids specified!");
    }
}
