import java.util.ArrayList;

/**
 * Represents the list of tasks managed by the chatbot.
 * Provides methods to add, remove, and find tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with existing tasks.
     *
     * @param tasks Existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param t Task to add.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes and returns the task at the given 1-based index.
     */
    public Task delete(int oneBasedIndex) throws BenException {
        validateOneBasedIndex(oneBasedIndex);
        return tasks.remove(oneBasedIndex - 1);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds all tasks containing the given keyword in their description.
     *
     * @param keyword Keyword to search for.
     * @return A list of tasks matching the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.contains(keyword)) {
                results.add(t);
            }
        }
        return results;
    }

    private void validateOneBasedIndex(int index) throws BenException {
        if (tasks.isEmpty()) {
            throw new BenException("Your task list is empty.");
        }
        if (index < 1 || index > tasks.size()) {
            throw new BenException(
                    String.format("Index out of range. Please use 1 to %d.", tasks.size())
            );
        }
    }

    /**
     * Marks the task at the given 1-based index as done.
     */
    public Task mark(int oneBasedIndex) throws BenException {
        validateOneBasedIndex(oneBasedIndex);
        Task t = tasks.get(oneBasedIndex - 1);
        t.markDone();
        return t;
    }

    /**
     * Marks the task at the given 1-based index as not done.
     */
    public Task unmark(int oneBasedIndex) throws BenException {
        validateOneBasedIndex(oneBasedIndex);
        Task t = tasks.get(oneBasedIndex - 1);
        t.markNotDone();
        return t;
    }




}
