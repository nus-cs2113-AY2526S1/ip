package Siao.task;

/**
 * Represents an abstract task with a description and completion status.
 * Serves as the base class for specific task types such as Todo, Deadline, or Event.
 * Each task automatically increases the global task counter upon creation.
 * Subclasses are expected to override methods such as {@link #getType()} and {@link #getDeadline()} if applicable.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCounter = 0;

    /**
     * Constructs a new task with the specified description.
     * The task is initially marked as not done and increments the global task counter.
     *
     * @param description the description of the task
     */
    public Task(String description) { // constructor of the task
        this.description = description;
        this.isDone = false;
    }

    public static int getTaskCounter() {
        return taskCounter;
    }

    public void reduceCounter() {
        taskCounter--;
    }

    public void increaseCounter() {
        taskCounter++;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    public String getDeadline() {
        return "";
    }

    public String getType() {
        return " ";
    }

    public String getDescription() {
        return description;
    }

    public void markDone(){
        isDone = true;
    }

    public void markUndone(){
        isDone = false;
    }

    /**
     * Returns a formatted string representation of the task,
     * including type, status, and description.
     *
     * @return the string representation of the task
     */
    public String toString() {
        return String.format("[%s][%s] %s\n",
                getType(), getStatusIcon(), this.description);
    }

}
