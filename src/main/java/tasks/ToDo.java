package tasks;

/**
 * Represents a to-do task
 * A to-do is a basic task with no specific date or time
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do task with the specified description
     * @param task The description of the to-do task
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Returns a string representation of the to-do task suitable for saving to a file
     * @return A formatted string for file storage
     */
    @Override
    public String toSaveString() {
        if (isDone) {
            return "T|1|" + name;
        }
        return "T|0|" + name;
    }

    /**
     * Returns a formatted string representation of the to-do task, including its type
     * @return A formatted string
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[T][X] " + name;
        }
        return "[T][ ] " + name;
    }
}
