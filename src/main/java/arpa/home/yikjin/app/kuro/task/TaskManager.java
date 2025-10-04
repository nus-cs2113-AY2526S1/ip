package arpa.home.yikjin.app.kuro.task;

import java.util.ArrayList;

import arpa.home.yikjin.app.kuro.storage.FileManager;

public final class TaskManager {
    private static final int TASKS_INIT_SIZE = 10;
    private static final ArrayList<Task> TASKS = new ArrayList<>(TASKS_INIT_SIZE);

    public static void addTask(final Task task) {
        addTask(task, true);
    }

    public static void addTask(final Task task, final boolean isSaveToDisk) {
        TASKS.add(task);

        if (isSaveToDisk) {
            FileManager.saveTask(task);
        }
    }

    public static Task getTask(final int taskIndex) {
        return TASKS.get(taskIndex);
    }

    public static int getNumTasks() {
        return TASKS.size();
    }

    public static boolean hasTasks() {
        return !TASKS.isEmpty();
    }

    public static void removeTask(final int taskIndex) {
        TASKS.remove(taskIndex);
        FileManager.deleteTask(taskIndex);
    }

    public static void setTaskDone(final int taskIndex, final boolean isDone) {
        final Task task = TASKS.get(taskIndex);

        task.setDone(isDone);
        FileManager.updateTask(taskIndex, task);
    }
}
