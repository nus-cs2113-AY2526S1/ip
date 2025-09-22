package prime.task;

/**
 * Represents a deadline task in the task management system.
 * <p>
 * A deadline task contains a description and a specific due date/time
 * indicated by the {@code by} field.
 * </p>
 */
public class Deadline extends Task {
    /** The deadline (due date/time) for the task. */
    protected String by;

    /**
     * Constructs a {@code Deadline} task with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The due date/time for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task,
     * including its type, completion status, description, and deadline.
     *
     * @return A formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + getTickUnTickIcon() + " " + super.description + " (by: " + by + ")";
    }

    /**
     * Returns the deadline (due date/time) of the task.
     *
     * @return The deadline as a string.
     */
    public String getBy() {
        return this.by;
    }
}
