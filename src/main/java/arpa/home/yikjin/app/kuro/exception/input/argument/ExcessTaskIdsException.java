package arpa.home.yikjin.app.kuro.exception.input.argument;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppArgumentException;

public class ExcessTaskIdsException extends AppArgumentException {
    @Serial
    private static final long serialVersionUID = 0L;

    public ExcessTaskIdsException() {
        super("too many task ids specified!");
    }
}
