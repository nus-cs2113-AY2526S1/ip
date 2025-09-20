package superidol.tasklist;

import superidol.task.Task;
import superidol.ui.Message;
import superidol.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    public ArrayList<Task> getTaskList() {
        return listOfTasks;
    }

    public void addTask(Task task, boolean announce) {
        listOfTasks.add(task);
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
                System.out.println((i + 1) + ". " + listOfTasks.get(i).getTask());
            }
            System.out.println(Message.separator);
        }
    }

    public void deleteTask(int taskId) {
        if (taskId >= 1 && taskId <= Task.taskCount) {
            Ui.respondDeletingTask(listOfTasks.get(taskId - 1));
            listOfTasks.remove(taskId - 1);
            Task.taskCount--;
        } else {
            Ui.respondOutOfListRange();
        }
    }

    public void markTask(int taskId, boolean isDone) {
        if (taskId >= 1 && taskId <= Task.taskCount) {
            Task targetTask = listOfTasks.get(taskId - 1);
            targetTask.setIsDone(isDone);
            Ui.respondMarking(targetTask, isDone);
        } else {
            Ui.respondOutOfListRange();
        }
    }
}
