package arpa.home.yikjin.app.kuro.task;

import java.util.ArrayList;

import arpa.home.yikjin.app.kuro.storage.FileManager;

/**
 * Class to manage tasks
 */
public final class TaskManager {
    private static final int TASKS_INIT_SIZE = 10;
    private static final ArrayList<Task> TASKS = new ArrayList<>(TASKS_INIT_SIZE);

    /**
     * Add a task to list and save file
     *
     * @param task Task to add to list and save file
     */
    public static void addTask(final Task task) {
        addTask(task, true);
    }

    /**
     * Add a task to list and optionally save file
     *
     * @param task         Task to add
     * @param isSaveToDisk Whether the task should be saved to the save file
     */
    public static void addTask(final Task task, final boolean isSaveToDisk) {
        TASKS.add(task);

        if (isSaveToDisk) {
            FileManager.saveTask(task);
        }
    }

    /**
     * Get the task at a given index
     *
     * @param taskIndex Task index
     *
     * @return Task at specified index
     */
    public static Task getTask(final int taskIndex) {
        return TASKS.get(taskIndex);
    }

    /**
     * Get the total number of tasks in list
     *
     * @return Total number of tasks in list
     */
    public static int getNumTasks() {
        return TASKS.size();
    }

    /**
     * Check if the list is not empty
     *
     * @return Whether the list has tasks
     */
    public static boolean hasTasks() {
        return !TASKS.isEmpty();
    }

    /**
     * Remove a task at the given index from the list and save file
     *
     * @param taskIndex Index of task to remove
     */
    public static void removeTask(final int taskIndex) {
        TASKS.remove(taskIndex);
        FileManager.deleteTask(taskIndex);
    }

    /**
     * Set if the task at the given index is done
     *
     * @param taskIndex Index of task to update
     * @param isDone    Whether the task is done
     */
    public static void setTaskDone(final int taskIndex, final boolean isDone) {
        final Task task = TASKS.get(taskIndex);

        task.setDone(isDone);
        FileManager.updateTask(taskIndex, task);
    }
}
