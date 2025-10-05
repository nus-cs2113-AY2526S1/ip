package arpa.home.yikjin.app.kuro.task;

/**
 * Deadline task type
 */
public final class Deadline extends Task {
    private final String due;

    /**
     * Create a deadline task
     *
     * @param name Title of the deadline task to create
     * @param due  When the deadline is due
     */
    public Deadline(final String name, final String due) {
        super(TaskType.DEADLINE, name);
        this.due = due;
    }

    /**
     * Get when the deadline task is due
     *
     * @return When the deadline task is due
     */
    public String getDue() {
        return due;
    }

    /**
     * Get the string representation of the deadline task
     *
     * @return String representation of deadline task
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), due);
    }
}
