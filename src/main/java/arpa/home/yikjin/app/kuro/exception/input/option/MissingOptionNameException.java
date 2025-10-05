package arpa.home.yikjin.app.kuro.exception.input.option;

import java.io.Serial;

import arpa.home.yikjin.app.kuro.exception.base.AppOptionException;

/**
 * Missing option name exception
 */
public final class MissingOptionNameException extends AppOptionException {
    @Serial
    private static final long serialVersionUID = 0L;

    /**
     * Create a missing command option name exception
     *
     * @param taskType   Type of task with the missing option name
     * @param optionName Option name that is missing
     * @param optionType Type that the option name accepts
     */
    public MissingOptionNameException(final String taskType, final String optionName, final String optionType) {
        super(taskType + " must have a '/" + optionName + "' " + optionType + " given!");
    }
}
