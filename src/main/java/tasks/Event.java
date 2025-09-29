package tasks;

/**
 * Represents an event task, which has a start and end time
 * It is a subclass of the {@link Task} class
 */
public class Event extends Task {
    private final String start;
    private final String end;

    /**
     * Constructs an Event task with the given name, start time, and end time
     * @param task The description or name of the event
     * @param start The start time of the event
     * @param end The end time of the event
     */
    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file
     * @return The formatted string for saving
     */
    @Override
    public String toSaveString() {
        if (isDone) {
            return "E|1|" + name + "|" + start + "|" + end;
        }
        return "E|0|" + name + "|" + start + "|" + end;
    }

    /**
     * Returns a human-readable string representation of the event, including its completion status and duration
     * @return The formatted string for display
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[E][X] " + name + " (from: " + start + " | to: " + end + ")";
        }
        return "[E][ ] " + name + " (from: " + start + " | to: " + end + ")";
    }
}
