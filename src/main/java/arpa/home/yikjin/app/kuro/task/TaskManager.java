package arpa.home.yikjin.app.kuro.task;

import java.util.ArrayList;

public final class TaskManager {
    private static final int TASKS_INIT_SIZE = 10;
    private static final ArrayList<Task> TASKS = new ArrayList<>(TASKS_INIT_SIZE);

    public static void addTask(final Task task) {
        TASKS.add(task);
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

    public static void removeTask(final int taskId) {
        TASKS.remove(taskId);
    }
}
