package prime.manager;

import prime.exceptions.InvalidTaskNumberException;
import prime.exceptions.PrimeException;
import prime.storage.Storage;
import prime.task.Task;
import prime.ui.UserInterface;

import java.util.ArrayList;

/**
 * Manages the list of tasks for the application.
 * <p>
 * Handles task creation, deletion, marking/unmarking, searching,
 * and persistence through the {@link Storage} class.
 * Provides interaction feedback through the {@link UserInterface}.
 * </p>
 */
public class TaskManager {
    private static final int MAX_TASKS = 100;
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a new {@code TaskManager}.
     * <p>
     * Loads existing tasks from storage into the internal task list.
     * </p>
     */
    public TaskManager() {
        this.storage = new Storage();
        this.tasks = new ArrayList<>(storage.load());
    }

    /**
     * Adds a new task to the task list if the maximum task limit is not reached.
     *
     * @param task the task to add
     * @param ui   the user interface to display feedback
     * @throws PrimeException if the maximum task limit is reached
     */
    public void addTask(Task task, UserInterface ui) throws PrimeException {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
            storage.save(tasks);
            ui.printIndented("Got it. I've added this task:");
            ui.printIndented(task.toString());
            ui.printIndented("Now you have " + tasks.size() + " tasks in your task list.");
        } else {
            throw new PrimeException("Task list is full Human! Maybe it’s time you finish some tasks.");
        }
    }

    /**
     * Deletes a task from the task list by its task number.
     *
     * @param taskNo the task number (1-based index) to delete
     * @param ui     the user interface to display feedback
     * @throws PrimeException if the task number is invalid
     */
    public void deleteTask(int taskNo, UserInterface ui) throws PrimeException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new InvalidTaskNumberException(taskNo, tasks.size());
        }
        Task task = tasks.remove(taskNo - 1);
        storage.save(tasks);
        ui.printIndented("Got it. I've deleted this task:");
        ui.printIndented(task.toString());
        ui.printIndented("Now you have " + tasks.size() + " tasks in your task list.");
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param ui the user interface to display feedback
     */
    public void listTasks(UserInterface ui) {
        if (tasks.isEmpty()) {
            ui.printIndented("No tasks have been added in your list yet.");
            return;
        }
        ui.printIndented("Here are your tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.printIndented((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param taskIndex the task index (1-based) to mark as done
     * @param ui        the user interface to display feedback
     */
    public void markTask(int taskIndex, UserInterface ui) {
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            ui.printIndented("Invalid task number!");
            return;
        }
        Task task = tasks.get(taskIndex - 1);
        task.setIsDone(true);
        storage.save(tasks);
        ui.printIndented("Nice! I've marked this task as done:");
        ui.printIndented("  " + task);
    }

    /**
     * Marks a task as not completed.
     *
     * @param taskIndex the task index (1-based) to unmark as done
     * @param ui        the user interface to display feedback
     */
    public void unmarkTask(int taskIndex, UserInterface ui) {
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            ui.printIndented("Invalid task number!");
            return;
        }
        Task task = tasks.get(taskIndex - 1);
        task.setIsDone(false);
        storage.save(tasks);
        ui.printIndented("OK, I've marked this task as not done yet:");
        ui.printIndented("  " + task);
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword the keyword to search for (case-insensitive)
     * @return a list of tasks that match the search
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(task);
            }
        }
        return results;
    }
}
