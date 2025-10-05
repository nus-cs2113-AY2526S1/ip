package arpa.home.yikjin.app.kuro.task;

/**
 * Base task class
 */
public abstract class Task {
    private final TaskType taskType;
    private final String name;
    private boolean isDone;

    /**
     * Create a task that is not done
     *
     * @param taskType Task type to create
     * @param name     Title of task to create
     */
    Task(final TaskType taskType, final String name) {
        this(taskType, name, false);
    }

    /**
     * Create a task that is done or not done
     *
     * @param taskType Task type to create
     * @param name     Title of task to create
     * @param isDone   Whether the task is done
     */
    Task(final TaskType taskType, final String name, final boolean isDone) {
        this.taskType = taskType;
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Get the current task type
     *
     * @return Task type of current task
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Get the title of the task
     *
     * @return Task title
     */
    public String getName() {
        return name;
    }

    /**
     * Get if the task is done
     *
     * @return If the task is done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Set if the task is done
     *
     * @param done If the task is done
     */
    public void setDone(final boolean done) {
        isDone = done;
    }

    /**
     * Get the string representation of the task
     *
     * @return String representation of current task
     */
    @Override
    public String toString() {
        return String.format("[%c] [%c] %s", taskType.toChar(), getStatusIcon(), name);
    }

    /**
     * Get the done status icon of the task
     *
     * @return Status icon if the task is done
     */
    char getStatusIcon() {
        return isDone ? 'X' : ' ';
    }
}
