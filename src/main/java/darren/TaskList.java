package darren;

import darren.task.Task;
import java.util.ArrayList;

/**
 * Manages the list of tasks, including methods to access, modify and search for tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList from existing ArrayList of tasks
     * @param tasks The list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the total number of tasks in the list.
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves the task at the specified index in the list.
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes the task at the specified index from the list.
     * @param index The index of the task to be removed.
     * @return The removed Task object.
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Adds a task to the end of the list.
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves the list of tasks for storage.
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches for the tasks that contain the specified keyword.
     * @param keyword The string to search for.
     * @return A new TaskList containing the matching tasks.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase();

        for (Task task : this.tasks) {
            if (task.toString().toLowerCase().contains(lowerCaseKeyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }
}