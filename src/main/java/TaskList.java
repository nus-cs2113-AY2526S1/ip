import java.util.ArrayList;

/**
 * Represents a list of tasks
 */
public record TaskList(ArrayList<Task> tasks) {

    /**
     * Adds a task to the list
     *
     * @param t task parameter
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Returns the task at the specified index
     *
     * @param index parameter
     * @return result
     */

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index
     *
     * @param index parameter
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list
     *
     * @return result
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of all tasks
     *
     * @return result
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }
}