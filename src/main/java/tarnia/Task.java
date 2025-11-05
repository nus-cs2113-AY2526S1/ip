package tarnia;

/**
 * Represents a generic task with a description, status, and task type.
 * Provides methods for updating status, retrieiving details.
 * Acts as the parent class for {@link Deadline} and {@link Event}.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    
    /**
     * Constructs a new Todo task with description.
     * 
     * @param description The actual task details.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "T";
    }

    /**
     * Updates a task's status to done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Updates a task's status to undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /** 
     * Retrieves the status of a task.
     * 
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Retrieves the description of a task
     * 
     * @return String of the task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the type of task
     * 
     * @return String of the tasktype, "todo", "deadline", or "event".
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Returns a String representation of the todo task.
     * Includes task type, status, and description.
     * 
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[" + taskType + "][" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the formatted string for saving the event task to storage.
     * Format: "T | isDone (0 or 1) | description".
     * 
     * @return Formatted string for storage.
     */
    public String toSaveFormat() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
