package Nova.task;

/**
 * Represents a generic Task with a description and a completion status.
 * This is an abstract class that serves as a base for specific task types
 * such as Todo, Deadline, and Event.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with the given description.
     * By default, a new task is not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return true if done, false otherwise
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "X" if done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }


    /**
     * Returns a string representation of the task, including its status icon and description.
     * <p>
     * Example: [ ] sell book
     *
     * @return formatted string of task status and description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
