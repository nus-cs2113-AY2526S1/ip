package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, remove, access, and get the size of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     * Typically used when loading tasks from storage.
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task at a given index.
     * @param index the 0-based index of the task
     * @return the task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at a given index.
     * @param index the 0-based index of the task to remove
     * @return the task that was removed
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks as an ArrayList.
     * @return list of all tasks
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
