package arpa.home.yikjin.app.kuro.exception.input.command;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppCommandException;

public final class MissingCommandException extends AppCommandException {
    @Serial
    private static final long serialVersionUID = 0L;

    public MissingCommandException() {
        super("no command given!");
    }
}
