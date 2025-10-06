package paddington.task;

/**
 * Represents an Event task in the Paddington application.
 * An Event task has a description, a status (done or not done), and a time range (from and to).
 * Inherits from the abstract {@link Task} class.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     * The task is initially marked as not done.
     *
     * @param description the description of the Event task
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        setFrom(from);
        setTo(to);
    }

    /**
     * Constructs a new Event task with the given description, status, start time, and end time.
     *
     * @param description the description of the Event task
     * @param isDone the status of the task (true if done, false if not)
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        setFrom(from);
        setTo(to);
    }

    /**
     * Sets the start time for the event.
     *
     * @param from the start time of the event
     */
    private void setFrom(String from) {
        this.from = from;
    }

    /**
     * Sets the end time for the event.
     *
     * @param to the end time of the event
     */
    private void setTo(String to) {
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including its status, description, and time range.
     *
     * @return a string representation of the Event task with type, status, description, and time range
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Formats the Event task into a string suitable for saving to storage.
     * Example:
     * E | 0 | Conference | Monday 2-4pm
     *
     * @return a string representation of the Event task with type, status, description, and time range for saving
     */
    @Override
    public String formatToSave() {
        return "E" + super.formatToSave() + DELIMITER + from + "-" + to;
    }

    /**
     * Creates an Event task from a saved string representation.
     * The string is split into segments to extract the description, status, and time range (from-to).
     * Example:
     * E | Status | Description | From-To
     *
     * @param saveString the string representation of the Event task in storage
     * @return an Event task object created from the saved string
     */
    public static Event formatFromSave(String saveString) {
        String[] segments = saveString.split(" \\| ");
        boolean isDone = segments[1].trim().equals("1");
        String description = segments[2].trim();
        String[] timings = segments[3].trim().split("-");
        String from = timings[0];
        String to = timings[1];
        return new Event(description, isDone, from, to);
    }
}
