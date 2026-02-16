/**
 * Represents a task with a that occurs within a certain time period.
 * An <code>Event</code> object extends {@link Task} and includes a start
 * and end time stored as a string.
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with the given description and time range.
     *
     * @param description Description of the task.
     * @param from Start time of the task.
     * @param to End time of the task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the task formatted as a string.
     *
     * @return Formatted task string.
     */
    @Override
    public String listTasks(){
        return "[E]" + super.listTasks() + " (from: " + from + " to: " + to + ")";
    }
}
