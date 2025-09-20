package superidol.tasklist;

import superidol.task.Deadline;
import superidol.task.Event;
import superidol.task.Task;
import superidol.ui.Message;
import superidol.ui.Ui;

import java.time.LocalDate;
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
            Ui.separate();
            for (int i = 0; i < Task.taskCount; i++) {
                System.out.println((i + 1) + ". " + taskList.get(i).getTask());
            }
            Ui.separate();
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
}
