package Chauncey.task;

public class Task {
    private String description;
    private boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // getter and setter of isDone
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns the status icon of a task.
     *
     * @return A String indicating the status of a task, where "X" means done, " " means undone.
     */
    public String getStatusIcon() {
        return (isDone ? "X":" ");
    }

    // getter and setter of description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Output a dummy line, to be overwritten by subclass.
     */
    public void outputTaskDetails() {
        System.out.println();
    }

    public String getTaskDetails() {
        return description;
    }

    /**
     * Returns a dummy String, to be overwritten by subclass.
     * @return Null
     */
    public String writeToFile() {
        return null;
    }
}
