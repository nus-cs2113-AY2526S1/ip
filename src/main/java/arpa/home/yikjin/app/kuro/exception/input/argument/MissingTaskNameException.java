package arpa.home.yikjin.app.kuro.exception.input.argument;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppArgumentException;

public final class MissingTaskNameException extends AppArgumentException {
    @Serial
    private static final long serialVersionUID = 0L;

    public MissingTaskNameException(final String taskType) {
        super(taskType + " name cannot be empty!");
    }
}
