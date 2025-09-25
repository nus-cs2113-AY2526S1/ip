package Nova.task;

import Nova.exception.NovaException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    private void checkTaskNumber(int taskNumber) throws NovaException {
        if (tasks.isEmpty()) {
            throw new NovaException("List is empty, no tasks found");
        }
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new NovaException("Invalid task number! Please enter a number from 1 to " + tasks.size() + ".");
        }
    }

    public Task deleteTask(int taskNumber) throws NovaException {
        checkTaskNumber(taskNumber);
        return tasks.remove(taskNumber - 1);
    }

    public Task getTask(int taskNumber) throws NovaException {
        checkTaskNumber(taskNumber);
        return tasks.get(taskNumber - 1);
    }


    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int getTasksSize() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

}
