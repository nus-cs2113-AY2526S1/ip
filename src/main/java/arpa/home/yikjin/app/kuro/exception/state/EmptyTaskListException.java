package arpa.home.yikjin.app.kuro.exception.state;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppStateException;

public final class EmptyTaskListException extends AppStateException {
    @Serial
    private static final long serialVersionUID = 0L;

    public EmptyTaskListException() {
        super("no tasks to show!");
    }
}
