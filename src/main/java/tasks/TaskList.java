package tasks;

import java.util.ArrayList;
import java.util.List;

import exceptions.PepException;

/**
 * Represents the list of tasks managed by the chatbot.
 * Provides methods to add, delete, mark, unmark, and search tasks.
 */
public record TaskList(ArrayList<Task> tasks) {
    public TaskList() {
        this(new ArrayList<>());
    }

    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
    }

    public int getCount() {
        return tasks.size();
    }

    /**
     * Validates that the given index is within the bounds of the task list.
     *
     * @param index the zero-based index to validate
     * @throws PepException if the index is out of range
     */
    public void validateIndex(int index) throws PepException {
        if (index < 0 || index >= tasks.size()) {
            throw new PepException("Task number " + (index + 1)
                    + " does not exist. You currently have "
                    + tasks.size() + " tasks.");
        }
    }

    /**
     * Returns the task at the given index after validation.
     *
     * @param index the zero-based index of the task
     * @return the Task at the given index
     * @throws PepException if the index is out of range
     */
    public Task getTask(int index) throws PepException {
        validateIndex(index);
        return tasks.get(index);
    }

    /**
     * Deletes the task at the given index after validation.
     *
     * @param index the zero-based index of the task
     * @return the removed Task
     * @throws PepException if the index is out of range
     */
    public Task deleteTask(int index) throws PepException {
        validateIndex(index);
        tasks.get(index).markAsNotDone();
        return tasks.remove(index);
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index the zero-based index of the task
     * @throws PepException if the index is out of range
     */
    public void markTask(int index) throws PepException {
        validateIndex(index);
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index the zero-based index of the task
     * @throws PepException if the index is out of range
     */
    public void unmarkTask(int index) throws PepException {
        validateIndex(index);
        tasks.get(index).markAsNotDone();
    }


    /**
     * Finds all tasks whose description contains the given keyword.
     *
     * @param keyword the keyword to search for
     * @return a list of matching tasks, or an empty list if none found
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }
}