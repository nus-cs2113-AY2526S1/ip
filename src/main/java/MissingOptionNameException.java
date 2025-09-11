import java.io.Serial;

final class MissingOptionNameException extends AppOptionException {
    @Serial
    private static final long serialVersionUID = 0L;

    MissingOptionNameException(final String taskType, final String optionName, final String optionType) {
        super(taskType + " must have a '/" + optionName + "' " + optionType + " given!");
    }
}
