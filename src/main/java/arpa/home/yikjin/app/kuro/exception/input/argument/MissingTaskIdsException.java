package arpa.home.yikjin.app.kuro.exception.input.argument;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppArgumentException;

public final class MissingTaskIdsException extends AppArgumentException {
    @Serial
    private static final long serialVersionUID = 0L;

    public MissingTaskIdsException() {
        super("task ids must be specified!");
    }
}
