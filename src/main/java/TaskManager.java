import java.util.ArrayList;

final class TaskManager {
    private static final int TASKS_INIT_SIZE = 10;
    private static final ArrayList<Task> TASKS = new ArrayList<>(TaskManager.TASKS_INIT_SIZE);

    static void addTask(final Task task) {
        TaskManager.TASKS.add(task);
    }

    static Task getTask(final int taskIndex) {
        return TaskManager.TASKS.get(taskIndex);
    }

    static int getNumTasks() {
        return TaskManager.TASKS.size();
    }

    static boolean hasTasks() {
        return !TaskManager.TASKS.isEmpty();
    }
}
