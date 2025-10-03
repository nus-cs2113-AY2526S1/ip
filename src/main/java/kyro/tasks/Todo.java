package kyro.tasks;

/**
 * Represents a to-do task.
 * <p>
 * A {@code Todo} is the simplest task type, containing only a description
 * and a completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this to-do task for display.
     *
     * @return A formatted string with type and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of this to-do task for saving to file.
     *
     * @return A formatted string with type, status, and description.
     */
    @Override
    public String toSave() {
        return "T | " + super.toSave();
    }
}
