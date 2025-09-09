import java.util.ArrayList;

final class TaskManager {
    private static final int TASKS_INIT_SIZE = 10;
    private static final ArrayList<Task> TASKS = new ArrayList<>(TASKS_INIT_SIZE);

    static void addTask(final Task task) {
        TASKS.add(task);
    }

    static Task getTask(final int taskIndex) {
        return TASKS.get(taskIndex);
    }

    static int getNumTasks() {
        return TASKS.size();
    }

    static boolean hasTasks() {
        return !TASKS.isEmpty();
    }
}
