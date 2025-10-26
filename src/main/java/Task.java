/**
 * Represents a general task with a description and completion status.
 * It is the base class for the other task types.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks the task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    /** Marks the task as not done. */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /** Returns the status icon "X" if task is marked as done, " " if not done.
     *
     * @return Status icon string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Returns a string with a task and its doneness.
     *
     * @return Display string.
     */
    public String listTasks(){
        return("[" + getStatusIcon() + "] " + description);
    }
}
