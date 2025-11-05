package tarnia;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();
    private final Storage storage;
    private final Ui ui;

    public TaskList(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
        try {
            this.tasks = storage.load();
        } catch (Exception e) {
            this.tasks = new ArrayList<>();
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getCount() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        ui.printAddTaskMessage(task);
        ui.printListCountMessage(tasks.size());
        saveTasks();
    }

    public void findTask(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(task);
            }
        }
        if (results.isEmpty()) {
            ui.printFoundNoTasks();
        } else {
            ui.printFoundTasks(results);
        }
    }

    public void markTask(int index) {
        tasks.get(index).markDone();
        ui.printMarkTaskMessage(tasks.get(index));
        saveTasks();
    }

    public void unmarkTask(int index) {
        tasks.get(index).markUndone();
        ui.printUnmarkTaskMessage(tasks.get(index));
        saveTasks();
    }

    public void deleteTask(int index) {
        ui.printDeleteMessage(tasks, index);
        tasks.remove(index);
        ui.printListCountMessage(tasks.size());
        saveTasks();
    }

    private void saveTasks() {
        try {
            storage.save(tasks);
        } catch (Exception e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
