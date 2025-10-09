package task;

/**
 * Represents a Deadline task, which is a type of Task with a due date/time.
 */
public class Deadline extends Task {
    /**
     * The due date/time of the deadline
     */
    protected String by;

    /**
     * Constructs a new Deadline with the given description and due date/time.
     *
     * @param description the description of the deadline task
     * @param by          the due date/time of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representing the status of this Deadline for display.
     * Format includes "[D]" prefix, status icon, and "(by: ...)".
     *
     * @return formatted status string for display
     */
    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon() + " (by: " + by + ")";
    }

    /**
     * Returns a string suitable for saving this Deadline to a file.
     * Format: "D | isDone | description | by"
     *
     * @return string for file storage
     */
    @Override
    public String toFileString() {
        return "D | " + isDone + " | " + description + " | " + by;
    }

    /**
     * Returns a string representation of this Deadline for display.
     * Includes "[D]" prefix, status icon, and "(by: ...)".
     *
     * @return string representation for display
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " (by: " + by + ")";
    }
}
