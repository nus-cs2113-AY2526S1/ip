package command;

/**
 * Represents a task that is to be done within a period of time
 *
 * @author  Lai Kai Jie Jeremy
 * @since   2025-08-17
 */

public class Events extends ToDo {
    private String from;
    private String to;
    private boolean eventStatus;


    public Events(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
        this.eventStatus = true;
    }

    /**
     * Returns the to field for events
     *
     * @return to
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the from field for deadline
     *
     * @return from
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns event status
     */
    @Override
    public String getType() {
        return (eventStatus ? "E" : " ");
    }

    /**
     * Returns event message
     */
    @Override
    public String getTask(){
        return super.getTask() + " (from: " + getFrom() + " to: " + getTo()  + ")";
    }
}
