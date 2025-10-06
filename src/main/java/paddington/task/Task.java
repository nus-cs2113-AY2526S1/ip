package paddington.task;

/**
 * Represents an abstract task in the Paddington application.
 * This class serves as the base class for different types of tasks: Todo, Deadline, Event.
 * Contains the task's description and status.
 */
public abstract class Task {
    protected final String DELIMITER = " | ";
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the given description and status.
     *
     * @param description the description of the task
     * @param isDone the status of the task (true if done, false if not)
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of the task.
     * "X" indicates the task is done, a space indicates it's not done.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return a string representing the task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Formats the task to a string for saving to storage.
     *
     * @return a string representation of the task for saving
     */
    public String formatToSave() {
        return DELIMITER + (isDone ? "1" : "0") + DELIMITER + description;
    };
}