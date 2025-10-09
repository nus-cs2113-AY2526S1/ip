package task;

/**
 * Represents a generic task in the task manager system.
 * Tracks the description, completion status, and task number.
 */
public class Task {
    /**
     * Description of the task
     */
    protected String description;

    /**
     * Status flag: 0 = not done, 1 = done
     */
    protected int isDone;

    /**
     * Counter to assign unique task numbers
     */
    protected static int numberOfTasks = 0;

    /**
     * Unique number assigned to this task
     */
    protected int taskNumber;

    /**
     * Constructs a new Task with the given description.
     * Assigns a unique task number automatically.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = 0;
        numberOfTasks++;
        this.taskNumber = Task.numberOfTasks;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = 1;
    }

    /**
     * Marks this task as not completed.
     */
    public void unMarkAsDone() {
        this.isDone = 0;
    }

    /**
     * Returns a string representing the status of this task.
     * Format: "[X] description" if done, "[ ] description" if not done.
     *
     * @return formatted status string
     */
    public String getStatusIcon() {
        return "[" + (isDone == 1 ? "X" : " ") + "] " + description;
    }

    /**
     * Returns a string representation of this task.
     * Currently delegates to getStatusIcon().
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return getStatusIcon();
    }

    /**
     * Returns a string representation of this task suitable for saving to a file.
     * Format: "T | isDone | description"
     *
     * @return string for file storage
     */
    public String toFileString() {
        return "T | " + isDone + " | " + description;
    }

    /**
     * Returns the unique task number assigned to this task.
     *
     * @return task number
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Returns the description of the task.
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }
}
