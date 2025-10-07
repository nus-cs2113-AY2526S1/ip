package prime.task;

/**
 * Represents a generic task in the task management system.
 * This is an abstract class that can be extended by specific task types
 * such as ToDo, Deadline, or Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * By default, the task is not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone true if the task is completed, false otherwise.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a tick or untick icon representing the task's status.
     *
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    public String getTickUnTickIcon() {
        if (this.getIsDone()) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns the string representation of the task.
     * This currently returns only the description.
     *
     * @return The task description.
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description string.
     */
    public String getDescription() {
        return this.description;
    }
}
