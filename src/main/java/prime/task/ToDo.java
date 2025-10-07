package prime.task;

/**
 * Represents a to-do task in the task management system.
 * <p>
 * A to-do task contains only a description with no specific deadline or time range.
 * </p>
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} task with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do task,
     * including its type, completion status, and description.
     *
     * @return A formatted string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + getTickUnTickIcon() + " " + super.description;
    }
}
