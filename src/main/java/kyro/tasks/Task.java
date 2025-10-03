package kyro.tasks;

/**
 * Represents a generic task in the Kyro application.
 * <p>
 * A {@code Task} has a description and a completion status.
 * Subclasses such as {@link Todo}, {@link Deadline}, and {@link Event}
 * add additional fields and behavior.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done or not done.
     *
     * @param isDone {@code true} to mark the task as done, {@code false} otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns whether this task is marked as done.
     *
     * @return {@code true} if the task is done; otherwise {@code false}.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of this task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "[X]" if the task is done, "[ ]" if not.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a string representation of this task for display.
     *
     * @return A formatted string with the status icon and description.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    /**
     * Returns a string representation of this task for saving to file.
     *
     * @return A formatted string with the completion flag and description.
     */
    public String toSave() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
