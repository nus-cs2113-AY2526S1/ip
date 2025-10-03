import java.util.ArrayList;

/**
 * Represents a task list containing all tasks in Sugon.
 * Provides methods to add, delete, mark, and unmark tasks.
 */
public class Tasklist {
    // loads the saved ArrayList of Tasks
    private ArrayList<Task> tasks;

    public Tasklist() {
        this.tasks = new ArrayList<>();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     */
    public void markTask(int index) {
        tasks.get(index).isDone = true;
    }

    /**
     * Unmarks the task at the specified index (sets it as not done).
     *
     * @param index The index of the task to unmark.
     */
    public void unmarkTask(int index) {
        tasks.get(index).isDone = false;
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to add.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes and returns the task at the specified index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns all tasks in the list.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
}
