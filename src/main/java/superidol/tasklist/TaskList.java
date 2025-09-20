package superidol.tasklist;

import superidol.task.Task;
import superidol.ui.Message;
import superidol.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task, boolean announce) {
        taskList.add(task);
        Task.taskCount++;
        if (announce) {
            Ui.respondAddingTask(task);
        }
    }

    public void showList() {
        if (Task.taskCount == 0) {
            Ui.respondEmptyList();
        } else {
            System.out.println(Message.separator);
            for (int i = 0; i < Task.taskCount; i++) {
                System.out.println((i + 1) + ". " + taskList.get(i).getTask());
            }
            System.out.println(Message.separator);
        }
    }

    public void deleteTask(int taskId) {
        if (taskId >= 1 && taskId <= Task.taskCount) {
            Ui.respondDeletingTask(taskList.get(taskId - 1));
            taskList.remove(taskId - 1);
            Task.taskCount--;
        } else {
            Ui.respondOutOfListRange();
        }
    }

    public Task getTask(int taskId) {
        return taskList.get(taskId);
    }
}
