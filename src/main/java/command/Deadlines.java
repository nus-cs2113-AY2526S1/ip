package command;

/**
 * Represents a task that is to be done by a deadline
 *
 * @author  Lai Kai Jie Jeremy
 * @since   2025-08-17
 */

public class Deadlines extends ToDo{
    private String by;
    private boolean deadlineStatus;

    public Deadlines(String taskName, String by) {
        super(taskName);
        this.by = by;
        this.deadlineStatus = true;
    }

    /**
     * Returns the by field for deadline
     *
     * @return by
     */
    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns the deadline status
     */
    @Override
    public String getType() {
        return (deadlineStatus ? "D" : " ");
    }

    /**
     * Returns deadline message
     */
    @Override
    public String getTask(){
        return super.getTask() + " (by: " + getBy() + ")";
    }
}
