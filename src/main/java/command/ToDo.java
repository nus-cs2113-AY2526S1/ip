package command;

/**
 * Represents a task that is to be done
 *
 * @author  Lai Kai Jie Jeremy
 * @since   2025-08-17
 */

public class ToDo extends Task{

    private boolean toDoStatus;

    public ToDo(String taskName) {
        super(taskName);
        this.toDoStatus = true;
    }

    /**
     * Returns todo status
     */
    public String getType() {
        return (toDoStatus ? "T" : " ");
    }

    /**
     * Returns the task message
     */
    @Override
    public String getTask(){
        return "[" + getType() + "] " +  super.getTask();
    }

    /**
     * Returns the from field for deadline
     *
     * @return from
     */
    @Override
    public String getFrom(){
        return "";
    }

    /**
     * Returns the to field for events
     *
     * @return to
     */
    @Override
    public String getTo(){
        return "";
    }

    /**
     * Returns the by field for deadline
     *
     * @return by
     */
    @Override
    public String getBy(){
        return "";
    }

}
