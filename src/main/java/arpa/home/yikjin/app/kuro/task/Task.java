package arpa.home.yikjin.app.kuro.task;

public abstract class Task {
    private final TaskType taskType;
    private final String name;
    private boolean isDone;

    Task(final TaskType taskType, final String name) {
        this(taskType, name, false);
    }

    Task(final TaskType taskType, final String name, final boolean isDone) {
        this.taskType = taskType;
        this.name = name;
        this.isDone = isDone;
    }

    TaskType getTaskType() {
        return taskType;
    }

    String getName() {
        return name;
    }

    boolean isDone() {
        return isDone;
    }

    void setDone(final boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return String.format("[%c] [%c] %s", taskType.toChar(), getStatusIcon(), name);
    }

    char getStatusIcon() {
        return isDone ? 'X' : ' ';
    }
}
