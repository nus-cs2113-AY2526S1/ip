package Nova.task;

import Nova.exception.NovaException;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Represents a list of tasks and provides operations to manipulate them.
 */

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the new task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Checks whether the specified task number is valid/within range.
     *
     * @param taskNumber the task number to check.
     * @throws NovaException If the list is empty or the task number is out of range.
     */
    private void checkTaskNumber(int taskNumber) throws NovaException {
        if (tasks.isEmpty()) {
            throw new NovaException("List is empty, no tasks found");
        }
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new NovaException("Invalid task number! Please enter a number from 1 to " + tasks.size() + ".");
        }
    }

    /**
     * Deletes and returns the task at the specified task number.
     *
     * @param taskNumber the task number of the task in the task list to delete.
     * @return the deleted task
     * @throws NovaException If the task number is invalid
     */
    public Task deleteTask(int taskNumber) throws NovaException {
        checkTaskNumber(taskNumber);
        return tasks.remove(taskNumber - 1);
    }

    /**
     * Retrieves the task at the specified task number.
     *
     * @param taskNumber the task number of the task in the task list
     * @return the task at the specified task number
     * @throws NovaException If the task number is invalid
     */
    public Task getTask(int taskNumber) throws NovaException {
        checkTaskNumber(taskNumber);
        return tasks.get(taskNumber - 1);
    }

    /**
     * Returns the full list of tasks.
     *
     * @return the internal list of tasks
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return true if the list contains no tasks, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Searches for tasks containing the given keyword in their description.
     *
     * @param keyword the keyword to search for
     * @return a new TaskList containing tasks whose descriptions contain the keyword
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = (ArrayList<Task>) tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(toList());
        return new TaskList(matchingTasks);
    }
}
