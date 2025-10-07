package rudeus.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructs a Deadline task with a description and a due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
