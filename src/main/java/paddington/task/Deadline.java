package paddington.task;

/**
 * Represents a Deadline task in the Paddington application.
 * A Deadline task has a description, a status (done or not done), and a deadline date.
 * Inherits from the abstract {@link Task} class.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline task with the given description and deadline date.
     * The task is initially marked as not done.
     *
     * @param description the description of the Deadline task
     * @param by the deadline date (when the task is due)
     */
    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    /**
     * Constructs a new Deadline task with the given description, status, and deadline date.
     *
     * @param description the description of the Deadline task
     * @param isDone the status of the task (true if done, false if not)
     * @param by the deadline date (when the task is due)
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        setBy(by);
    }

    /**
     * Sets the deadline date for the task.
     *
     * @param by the deadline date (when the task is due)
     */
    private void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its status, description, and deadline.
     *
     * @return a string representation of the Deadline task with type, status, description and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Formats the Deadline task into a string suitable for saving to storage.
     * Example:
     * D | 0 | Finish Homework | Monday 4pm
     *
     * @return a string representation of the Deadline task with type, status, description, and deadline for saving
     */
    @Override
    public String formatToSave() {
        return "D" + super.formatToSave() + DELIMITER + by;
    }

    /**
     * Creates a Deadline task from a saved string representation.
     * The string is split into segments to extract the description, status, and deadline.
     * Example:
     * D | Status | Description | Deadline
     *
     * @param saveString the string representation of the Deadline task in storage
     * @return a Deadline task object created from the saved string
     */
    public static Deadline formatFromSave(String saveString) {
        String[] segments = saveString.split(" \\| ");
        boolean isDone = segments[1].trim().equals("1");
        String description = segments[2].trim();
        String by = segments[3].trim();
        return new Deadline(description, isDone, by);
    }
}
