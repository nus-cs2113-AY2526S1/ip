/**
 * Represents a generic task with a textual description and a completion state.
 *
 * <p>This is the base class for others
 * It tracks whether the task is done and formats a compact status icon used by the UI.</p>
 *
 * @author  Xylon
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {//constructor
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }


    public String toString() {
        return "[" + getStatusIcon() + "] " + description; //automatically called when system.out.print(object)
    }
}