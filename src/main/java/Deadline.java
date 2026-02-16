/**
 * Represents a task with a deadline. A <code>Deadline</code> object extends
 * {@link Task} and includes a due date stored as a string.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the task formatted as a string.
     *
     * @return Formatted task string.
     */
    @Override
    public String listTasks() {
        return "[D]" + super.listTasks() + " (by: " + by + ")";
    }
}