package prime.task;


/**
 * Represents an event task in the task management system.
 * <p>
 * An event task contains a description and a time range defined by
 * a start time ({@code from}) and an end time ({@code to}).
 * </p>
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an {@code Event} task with the given description and time range.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task,
     * including its type, completion status, description, and time range.
     *
     * @return A formatted string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + getTickUnTickIcon() + " " + super.description + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time as a string.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time as a string.
     */
    public String getTo() {
        return this.to;
    }
}
