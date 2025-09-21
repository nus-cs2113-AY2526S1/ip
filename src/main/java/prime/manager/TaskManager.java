package prime.manager;

import prime.exceptions.InvalidTaskNumberException;
import prime.exceptions.PrimeException;
import prime.storage.Storage;
import prime.task.Task;
import prime.ui.UserInterface;

import java.util.ArrayList;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskManager() {
        this.storage = new Storage();
        this.tasks = new ArrayList<>(storage.load());
    }

    public void addTask(Task task, UserInterface ui) throws PrimeException {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
            storage.save(tasks);
            ui.printIndented("Got it. I've added this task:");
            ui.printIndented(task.toString());
            ui.printIndented("Now you have " + tasks.size() + " tasks in your task list.");
        } else {
            throw new PrimeException("Task list is full Human! Maybe it’s time you finish some tasks.");
        }
    }

    public void deleteTask(int taskNo, UserInterface ui) throws PrimeException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new InvalidTaskNumberException(taskNo, tasks.size());
        }
        Task task = tasks.remove(taskNo - 1);
        storage.save(tasks);
        ui.printIndented("Got it. I've deleted this task:");
        ui.printIndented(task.toString());
        ui.printIndented("Now you have " + tasks.size() + " tasks in your task list.");
    }

    public void listTasks(UserInterface ui) {
        if (tasks.isEmpty()) {
            ui.printIndented("No tasks have been added in your list yet.");
            return;
        }
        ui.printIndented("Here are your tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.printIndented((i + 1) + "." + tasks.get(i));
        }
    }
    public void markTask(int taskIndex, UserInterface ui) {
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            ui.printIndented("Invalid task number!");
            return;
        }
        Task task = tasks.get(taskIndex - 1);
        task.setIsDone(true);
        ui.printIndented("Nice! I've marked this task as done:");
        ui.printIndented("  " + task);
    }

    public void unmarkTask(int taskIndex, UserInterface ui) {
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            ui.printIndented("Invalid task number!");
            return;
        }
        Task task = tasks.get(taskIndex - 1);
        task.setIsDone(false);
        ui.printIndented("OK, I've marked this task as not done yet:");
        ui.printIndented("  " + task);
    }
}
