package kyro.base;

import kyro.tasks.*;
import kyro.exceptions.*;

import java.util.ArrayList;

/**
 * Manages a dynamic list of {@link Task} objects.
 * <p>
 * Provides operations for adding, removing, retrieving, and
 * displaying tasks within Kyro.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to delete.
     */
    public void remove(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the given index.
     * @throws InvalidTaskNumberException If the index is invalid.
     */
    public Task get(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the full list of tasks.
     *
     * @return The underlying {@link ArrayList} of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return new ArrayList<>(tasks);
    }
}
