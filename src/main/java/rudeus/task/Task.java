package rudeus.task;

/**
 * Abstract base class for all tasks.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with a description and sets its completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone True if the task is done, false otherwise.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + getDescription();
    }
}
