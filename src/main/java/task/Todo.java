package task;

/**
 * Represents a Todo task, which is a type of Task without any date/time.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the given description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representing the status of this Todo.
     * Format: "[T][X] description" if done, "[T][ ] description" if not done.
     *
     * @return formatted status string for display
     */
    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    /**
     * Returns a string suitable for saving this Todo to a file.
     * Format: "T | isDone | description"
     *
     * @return string for file storage
     */
    @Override
    public String toFileString() {
        return "T | " + isDone + " | " + description;
    }

    /**
     * Returns a string representation of this Todo for display.
     * Delegates to getStatusIcon() with "[T]" prefix.
     *
     * @return string representation for display
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon();
    }
}
