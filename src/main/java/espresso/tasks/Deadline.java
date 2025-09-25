package espresso.tasks;

/**
 * Represents a task with a specific deadline.
 * Extends {@link Task} and adds deadline metadata for display and serialization.
 */
public class Deadline extends Task {

    private final String deadline;

    /**
     * Constructs a new {@code Deadline} task with the given description and deadline.
     * The task is initially marked as not done.
     *
     * @param description the task description
     * @param deadline    the due date or time for the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a new {@code Deadline} task with the given description, deadline, and completion status.
     *
     * @param description the task description
     * @param deadline    the due date or time for the task
     * @param marked      {@code true} if the task is completed; {@code false} otherwise
     */
    public Deadline(String description, String deadline, boolean marked) {
        super(description);
        super.setIsDone(marked);
        this.deadline = deadline;
    }

    /**
     * Returns the task type code used in file serialization.
     *
     * @return {@code "D"} for deadline tasks
     */
    @Override
    protected String getType() {
        return "D";
    }

    /**
     * Returns a formatted status line for display.
     * Includes task type, completion status, description, and deadline.
     *
     * @return formatted status line
     */
    @Override
    public String getStatusLine() {
        return "[D]" +
                "[" + this.getStatusIcon() + "] " +
                this.getDescription() +
                " (by: " + this.deadline + ")";
    }

    /**
     * Returns a serialized representation of the task for file storage.
     * Format: {@code D | done | description | deadline}
     *
     * @return serialized task line
     */
    @Override
    public String getFileLine() {
        return super.getFileLine() + " | " + this.deadline;
    }
}