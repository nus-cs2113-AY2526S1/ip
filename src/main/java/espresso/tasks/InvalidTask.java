package espresso.tasks;

/**
 * Represents a placeholder task used when input parsing fails.
 * This task is marked invalid and excluded from normal task operations.
 */
public class InvalidTask extends Task {

    /**
     * Constructs an {@code InvalidTask} with an empty description.
     * Sets the task validity flag to {@code false}.
     */
    public InvalidTask() {
        super("");
        setValid(false);
    }

    /**
     * Returns an empty string as the task type.
     *
     * @return empty string representing an invalid type
     */
    @Override
    protected String getType() {
        return "";
    }
}