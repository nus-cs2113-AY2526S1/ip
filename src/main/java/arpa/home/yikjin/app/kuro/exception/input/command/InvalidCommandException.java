package arpa.home.yikjin.app.kuro.exception.input.command;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppCommandException;

public final class InvalidCommandException extends AppCommandException {
    @Serial
    private static final long serialVersionUID = 0L;

    public InvalidCommandException(final String command) {
        super("unknown command '" + command + "'");
    }
}
