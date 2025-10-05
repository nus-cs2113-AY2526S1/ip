package arpa.home.yikjin.app.kuro.exception.input.option;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppOptionException;

/**
 * Missing option value exception
 */
public final class MissingOptionValueException extends AppOptionException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a missing command option value exception
     *
     * @param taskType   Type of task with the missing option value
     * @param optionName Option name that is missing its value
     * @param optionType Type that the option name accepts
     */
    public MissingOptionValueException(final String taskType, final String optionName, final String optionType) {
        super(taskType + " must have a " + optionType + " given after '/" + optionName + "'!");
    }
}
