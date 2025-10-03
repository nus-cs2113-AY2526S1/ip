package chara.task;

/**
 * Represents a task that must be completed by a specific deadline.
 * Stores a description and a "/by" time string.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a chara.task.Deadline task with the given description and due date.
     *
     * @param description description of the task
     * @param by deadline date/time as a string
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getTaskTypeIcon() {
        return "[D]";
    }

    @Override
    public void printTask() {
        System.out.print(taskTypeIcon + statusIcon + ' ' + description + " (by: " + by + ")");
    }
}
