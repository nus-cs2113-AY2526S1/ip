/**
 * Base class for all task types
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description. Newly created tasks are not done
     *
     * @param description information on task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Marks this task as done */
    public void markAsDone() {
        if (!isDone) {
            isDone = true;
        }
    }

    /** Marks this task as not done */
    public void markAsUndone() {
        if (isDone) {
            isDone = false;
        }
    }

    /** Present task information in tasklist **/
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}