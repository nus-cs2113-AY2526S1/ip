package espresso.tasks;

/**
 * Represents a simple task without time constraints.
 * Extends {@link Task} and uses type code {@code "T"} for serialization.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new {@code Todo} task with the given description and completion status.
     *
     * @param description the task description
     * @param marked      {@code true} if the task is completed; {@code false} otherwise
     */
    public Todo(String description, boolean marked) {
        super(description);
        super.setIsDone(marked);
    }

    /**
     * Returns the task type code used in file serialization.
     *
     * @return {@code "T"} for todo tasks
     */
    public String getType() {
        return "T";
    }

    /**
     * Returns a formatted status line for display.
     * Includes task type, completion status, and description.
     *
     * @return formatted status line
     */
    @Override
    public String getStatusLine() {
        return "[T]" +
                "[" + this.getStatusIcon() + "] " +
                this.getDescription();
    }
}