package chara.task;

/**
 * Represents a simple task without any date or time attached.
 */
public class Todo extends Task {
    /**
     * Constructs a chara.task.Todo task with the given description.
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return "[T]";
    }
}
