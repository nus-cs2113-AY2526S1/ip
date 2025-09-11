import java.io.Serial;

final class MissingOptionValueException extends AppOptionException {
    @Serial
    private static final long serialVersionUID = 0L;

    MissingOptionValueException(final String taskType, final String optionName, final String optionType) {
        super(taskType + " must have a " + optionType + " given after '/" + optionName + "'!");
    }
}
