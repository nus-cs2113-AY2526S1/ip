package arpa.home.yikjin.app.kuro.exception.input.option;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppOptionException;

public final class MissingOptionValueException extends AppOptionException {
    @Serial
    private static final long serialVersionUID = 0L;

    public MissingOptionValueException(final String taskType, final String optionName, final String optionType) {
        super(taskType + " must have a " + optionType + " given after '/" + optionName + "'!");
    }
}
