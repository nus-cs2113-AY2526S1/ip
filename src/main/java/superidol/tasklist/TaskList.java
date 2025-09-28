package superidol.tasklist;

import superidol.task.Deadline;
import superidol.task.Event;
import superidol.task.Task;
import superidol.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents list of user tasks
 * Using an ArrayList<Task>
 */
public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Add new task into list
     * Choose to announce the result or not
     *
     * @param task
     * @param announce
     */
    public void addTask(Task task, boolean announce) {
        taskList.add(task);
        Task.taskCount++;
        if (announce) {
            Ui.respondAddingTask(task);
        }
    }

    /**
     * Print the whole list
     */
    public void showList() {
        if (Task.taskCount == 0) {
            Ui.respondEmptyList();
        } else {
            Ui.separate();
            for (int i = 0; i < Task.taskCount; i++) {
                System.out.println((i + 1) + ". " + taskList.get(i).getTask());
            }
            Ui.separate();
        }
    }

    /**
     * Delete a specific task in the list
     *
     * @param taskId
     */
    public void deleteTask(int taskId) {
        if (taskId >= 1 && taskId <= Task.taskCount) {
            Ui.respondDeletingTask(taskList.get(taskId - 1));
            taskList.remove(taskId - 1);
            Task.taskCount--;
        } else {
            Ui.respondOutOfListRange();
        }
    }

    /**
     * Get task from list by ID
     *
     * @param taskId
     * @return
     */
    public Task getTask(int taskId) {
        return taskList.get(taskId);
    }

    /**
     * Print list of tasks that are still valid
     *
     * @param time
     */
    public void printByTime(LocalDate time) {
        boolean isEmpty = true;
        Ui.separate();
        for (Task task : taskList) {
            if (task instanceof Deadline && ((Deadline) task).isValid(time)) {
                System.out.println(task.getTask());
                isEmpty = false;
            } else if (task instanceof Event && ((Event) task).isValid(time)) {
                System.out.println(task.getTask());
                isEmpty = false;
            }
        }
        if (isEmpty) {
            Ui.respondEmptyPrint();
        }
        Ui.separate();
    }

    /**
     * Find list of tasks by keyword
     *
     * @param keyword
     */
    public void findByKeyword(String keyword) {
        int count = 1;
        Ui.separate();
        Ui.respondValidFind();
        for (Task task : taskList) {
            if (task.contains(keyword)) {
                String result = count + ". " + task.getTask();
                System.out.println(result);
                count++;
            }
        }
        if (count == 1) {
            Ui.respondEmptyFind();
        }
        Ui.separate();
    }
}
