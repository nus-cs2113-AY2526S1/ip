package superidol.task;

public abstract class Task {
    public static int taskCount = 0;
    protected String task;
    protected boolean isDone = false;

    public Task() {}

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

    public boolean contains(String keyword) {
        return task.toLowerCase().contains(keyword.toLowerCase());
    }

    public abstract String saveData();
}