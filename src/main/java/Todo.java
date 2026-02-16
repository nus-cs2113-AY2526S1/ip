/**
 * Represents a To-do task
 */
public class Todo extends Task {


    /**
     * Creates a new To-do task
     *
     * @param description parameter.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of To-do
     *
     * @return result.
     */
    @Override public String toString() {
        return "[T] " + super.toString();
    }
}
