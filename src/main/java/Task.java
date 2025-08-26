public class Task {
    public static int taskCount = 0;
    private String task;
    private boolean isDone = false;

    public Task(String task) {
        this.task = task;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getTask() {
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}