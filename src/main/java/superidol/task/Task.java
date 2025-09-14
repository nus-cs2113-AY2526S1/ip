package superidol.task;

public abstract class Task {
    public static int taskCount = 0;
    protected String task;
    protected boolean isDone = false;

    public Task(String task) {
        this.task = task;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTask() {
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }

    public abstract String saveData();
}