package superidol.task;

public abstract class Task {
    public static int taskCount = 0;
    protected String task;
    protected boolean isDone = false;

    public Task() {}

    /**
     * mark/unmark the task
     *
     * @param isDone
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * get string of task's info to print
     *
     * @return String of task
     */
    public String getTask() {
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }

    /**
     * to find task by keyword
     *
     * @param keyword
     * @return boolean result: contains or not
     */
    public boolean contains(String keyword) {
        return task.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * return data to save in txt file
     *
     * @return String data
     */
    public abstract String saveData();
}