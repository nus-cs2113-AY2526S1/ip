package zoro.model;

/**
 * Represents a task with a deadline.
 * Extends the basic Task class to include deadline information.
 */
public class Deadline extends Task {
    protected String deadlineBy;

    /**
     * Constructs a new Deadline task with description and deadline.
     *
     * @param description - the description of the task
     * @param deadlineBy - the deadline for completing the task
     */
    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
        this.taskType = "[D]";
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return - formatted string showing task details with deadline
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + deadlineBy + ")";
    }
}
