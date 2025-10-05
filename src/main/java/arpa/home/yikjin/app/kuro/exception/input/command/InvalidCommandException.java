package arpa.home.yikjin.app.kuro.exception.input.command;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppCommandException;

/**
 * Invalid command name exception
 */
public final class InvalidCommandException extends AppCommandException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create an invalid command name exception
     *
     * @param command Invalid command name given
     */
    public InvalidCommandException(final String command) {
        super("unknown command '" + command + "'");
    }
}
