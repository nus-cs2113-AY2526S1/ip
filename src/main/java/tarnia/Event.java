package tarnia;

/**
 * Represents an event task with a task type, description, start time,
 * and end time.
 * Extends the Task class and provides overriding methods for displaying
 * and saving the event task.
 */
public class Event extends Task {
    private String from;
    private String to;
    
    /**
     * Constructs a new Event task with description,
     * start time, and end time.
     * 
     * @param description The actual task details.
     * @param from The start time.
     * @param to The end time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = "E";
    }
    
    /**
     * Returns a String representation of the event task.
     * Includes task type, status, description, start time, and
     * end time.
     * 
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the formatted string for saving the event task to storage.
     * Format: "E | isDone (0 or 1) | description | start time | end time".
     * 
     * @return Formatted string for storage.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + from + " | " + to;
    }
}