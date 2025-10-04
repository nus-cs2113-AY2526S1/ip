package command;

/**
 * Represents a task
 *
 * @author  Lai Kai Jie Jeremy
 * @since   2025-08-17
 */

public abstract class Task {
    protected String taskName;
    protected boolean markStatus;

    public Task(String taskName) {
        this.taskName = taskName;
        this.markStatus = false;
    }

    /**
     * Returns the task name
     *
     * @return taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns mark status
     */
    public String getMarkStatus(){
        return (markStatus ? "X" : " ");
    }

    /**
     * Sets the mark status
     *
     * @param markStatus whether it is mark or unmark
     */
    public void setMarkStatus(boolean markStatus) {
        this.markStatus = markStatus;
    }

    /**
     * Returns the task message
     */
    public String getTask(){
        return "[" + getMarkStatus() + "] " +  getTaskName();
    }

    /**
     * Returns type of whether it is todo, deadline or event
     *
     * @return T, D or E
     */
    public abstract String getType();

    /**
     * Returns the by field for deadline
     *
     * @return by
     */
    public abstract String getBy();

    /**
     * Returns the from field for deadline
     *
     * @return from
     */
    public abstract String getFrom();

    /**
     * Returns the to field for events
     *
     * @return to
     */
    public abstract String getTo();
}
