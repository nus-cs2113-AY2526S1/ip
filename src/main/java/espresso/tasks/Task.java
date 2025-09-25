package espresso.tasks;

/**
 * Abstract base class representing a generic task.
 * Encapsulates common properties such as description, completion status, and validity.
 * Subclasses must implement {@link #getType()} to specify their task type.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    public boolean isValid = true;

    /**
     * Constructs a new task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the textual description of the task
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone {@code true} if the task is completed; {@code false} otherwise
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return {@code true} if the task is completed; {@code false} otherwise
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns whether the task is considered valid.
     *
     * @return {@code true} if the task is valid; {@code false} otherwise
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * Sets the validity status of the task.
     *
     * @param valid {@code true} if the task is valid; {@code false} otherwise
     */
    public void setValid(boolean valid) {
        isValid = valid;
    }

    /**
     * Returns a status icon representing the task's completion state.
     * "X" for done, blank space for not done.
     *
     * @return status icon string
     */
    protected String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns a formatted status line for display.
     * Includes the status icon and task description.
     *
     * @return formatted status line
     */
    public String getStatusLine() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Returns the task type code used in file serialization.
     * Must be implemented by subclasses (e.g., "T", "D", "E").
     *
     * @return task type code
     */
    protected abstract String getType();

    /**
     * Returns a serialized representation of the task for file storage.
     * Format: {@code type | done | description}
     *
     * @return serialized task line
     */
    public String getFileLine() {
        String done = this.getIsDone() ? "1" : "0";
        return this.getType() + " | "
                + done + " | "
                + this.getDescription();
    }
}