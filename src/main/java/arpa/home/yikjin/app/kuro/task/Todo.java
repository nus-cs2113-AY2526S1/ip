package arpa.home.yikjin.app.kuro.task;

/**
 * Todo task type
 */
public final class Todo extends Task {
    /**
     * Create a todo task
     *
     * @param name Title of todo task to create
     */
    public Todo(final String name) {
        super(TaskType.TODO, name);
    }
}
