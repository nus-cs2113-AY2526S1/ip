package paddington.task;

/**
 * Represents a Todo task in the Paddington application.
 * A Todo task has a description and a status (done or not done).
 * Inherits from the abstract {@link Task} class.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the description of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo task with the given description and status.
     *
     * @param description the description of the Todo task
     * @param isDone the status of the task (true if done, false if not)
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the Todo task, including its status and description.
     *
     * @return a string representation of the Todo task with type, status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the Todo task into a string suitable for saving to storage.
     * Example:
     * T | 0 | Study
     *
     * @return a string representation of the Todo task with type, status and description for saving.
     */
    @Override
    public String formatToSave() {
        return "T" + super.formatToSave();
    }

    /**
     * Creates a Todo task from a saved string representation.
     * The string is split into segments to extract the description and status.
     * Example:
     * T | Status | Description
     *
     * @param saveString the string representation of the Todo task in storage
     * @return a Todo task object created from the saved string
     */
    public static Todo formatFromSave(String saveString) {
        String[] segments = saveString.split(" \\| ");
        boolean isDone = segments[1].trim().equals("1");
        String description = segments[2].trim();
        return new Todo(description, isDone);
    }
}
