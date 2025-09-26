package Nova.task;

/**
 * Represents a Todo task which has only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description the description of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task for display.
     * <p>
     * Example: [T][ ] read book
     *
     * @return the string representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
