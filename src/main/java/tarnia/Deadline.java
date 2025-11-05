package tarnia;

/**
 * Represents a deadline task with a task type, description and due date.
 * Extends the Task Class and provides overriding methods for displaying and 
 * saving deadlines.
 */
public class Deadline extends Task {
    private String by;
    
    /**
     * Constructs a new Deadline task with description and due date.
     * 
     * @param description The actual task details.
     * @param by The due date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    /**
     * Returns a String representation of the deadline task.
     * Includes task type, status, description, and due date.
     * 
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the formatted string for saving the deadline task to storage.
     * Format: "D | isDone (0 or 1) | description | due date".
     * 
     * @return Formatted string for storage.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
    }
}