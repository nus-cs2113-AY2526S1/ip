package espresso.tasks;

/**
 * Represents a task with a start and end time, such as a scheduled event.
 * Extends {@link Task} and adds time range metadata for display and serialization.
 */
public class Event extends Task {
    private final String start;
    private final String end;

    /**
     * Constructs a new {@code Event} task with the given description and time range.
     * The task is initially marked as not done.
     *
     * @param description the task description
     * @param start       the start time of the event
     * @param end         the end time of the event
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new {@code Event} task with the given description, time range, and completion status.
     *
     * @param description the task description
     * @param start       the start time of the event
     * @param end         the end time of the event
     * @param marked      {@code true} if the task is completed; {@code false} otherwise
     */
    public Event(String description, String start, String end, boolean marked) {
        super(description);
        super.setIsDone(marked);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the task type code used in file serialization.
     *
     * @return {@code "E"} for event tasks
     */
    @Override
    protected String getType() {
        return "E";
    }

    /**
     * Returns a formatted status line for display.
     * Includes task type, completion status, description, and time range.
     *
     * @return formatted status line
     */
    @Override
    public String getStatusLine() {
        return "[E]" +
                "[" + this.getStatusIcon() + "] " +
                this.getDescription() +
                " (from: " + this.start + " to: " + this.end + ")";
    }

    /**
     * Returns a serialized representation of the task for file storage.
     * Format: {@code E | done | description | start | end}
     *
     * @return serialized task line
     */
    @Override
    public String getFileLine() {
        return super.getFileLine() + " | " + this.start + " | " + this.end;
    }
}