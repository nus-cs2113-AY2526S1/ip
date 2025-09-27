package zoro.model;

/**
 * Represents a basic task with description and completion status.
 * Base class for more specific task types like Deadline and Event.
 */
public class Task {
    String taskType;
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * Task is initially marked as not done.
     *
     * @param description - the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "[T]";
    }

    /**
     * Gets the task description.
     *
     * @return - the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is completed.
     *
     * @return - true if task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Toggles the completion status of the task.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return - formatted string showing task type, completion status, and description
     */
    @Override
    public String toString() {
        return taskType + " " + (this.isDone ? "[X] " : "[ ] ") + description;
    }
}
