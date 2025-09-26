package tasks;

/**
 * Represents a task with a specific deadline
 * It is a subclass of the {@link Task} class
 */
public class Deadline extends Task {

    private final String deadline;

    /**
     * Constructs Deadline task with the given name and a deadline
     * @param task The description or name of the task
     * @param deadline The deadline time of the task
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file
     * @return The formatted string for saving
     */
    @Override
    public String toSaveString() {
        if (isDone) {
            return "D|1|" + name + "|" + deadline;
        }
        return "D|0|" + name + "|" + deadline;
    }

    /**
     * Returns a human-readable string representation of the deadline, including its completion status and due date
     * @return The formatted string for display
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[D][X] " + name + " (by: " + deadline + ")";
        }
        return "[D][ ] " + name + " (by: " + deadline + ")";
    }
}
