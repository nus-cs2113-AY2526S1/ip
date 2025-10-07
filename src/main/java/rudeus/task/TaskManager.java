package rudeus.task;

import java.util.ArrayList;
import java.util.List;

import rudeus.command.Parser;
import rudeus.storage.LocalSave;
import rudeus.storage.TaskSerializer;
import rudeus.ui.Ui;

/**
 * Manages the list of tasks and provides operations to manipulate them.
 */
public class TaskManager {
    private final List<Task> taskList = new ArrayList<>();
    private final String saveFilePath;

    /**
     * Constructs a TaskManager with the specified file path for saving tasks.
     *
     * @param saveFilePath The file path where tasks will be saved and loaded from.
     */
    public TaskManager(String saveFilePath) {
        this.saveFilePath = saveFilePath;
        loadTasksFromFile();
    }

    /**
     * Loads tasks from file at startup.
     */
    public void loadTasksFromFile() {
        List<String> lines = LocalSave.loadFromFile(saveFilePath);
        List<Task> loaded = TaskSerializer.deserializeTasks(lines);
        if (!loaded.isEmpty()) {
            taskList.clear();
            taskList.addAll(loaded);
        }
    }

    /**
     * Adds a task to the task list after parsing the user input.
     *
     * @param userInput The user input string containing task details.
     */
    public void addTask(String userInput) {
        try {
            Task task = Parser.parseTask(userInput);
            taskList.add(task);
            Ui.printMessageWithBorders("added: " + taskList.get(taskList.size() - 1));
            LocalSave.saveToFile(saveFilePath, TaskSerializer.serializeTasks(taskList));
        } catch (IllegalArgumentException e) {
            Ui.printMessageWithBorders(e.getMessage());
        }
    }

    /**
     * Prints the list of tasks with proper indentation.
     */
    public void printTaskList() {
        if (taskList.isEmpty()) {
            Ui.printMessageWithBorders("No tasks available.");
            return;
        }
        StringBuilder taskListMessage = new StringBuilder("Here are the tasks in your list:\n");
        String indent = Ui.getIndent(); // 4 spaces for normal indentation
        for (int i = 0; i < taskList.size(); i++) {
            taskListMessage.append(indent)
                    .append((i + 1))
                    .append(". ")
                    .append(taskList.get(i).toString())
                    .append("\n");
        }
        Ui.printMessageWithBorders(taskListMessage.toString().trim());
    }

    /**
     * Marks a task as done or not done based on the provided index and status.
     *
     * @param index  The index of the task in the task list (0-based).
     * @param isDone True to mark as done, false to mark as not done.
     */
    public void markTaskIsDone(int index, boolean isDone) {
        String extraIndent = Ui.getExtraIndent(); // 6 spaces for extra indentation
        if (index < 0 || index >= taskList.size()) {
            Ui.printMessageWithBorders("Oi! That task number doesn't even exist. Are you trying to trick me?");
            return;
        }
        if (isDone) {
            if (taskList.get(index).getIsDone()) {
                Ui.printMessageWithBorders("Task is already marked as done:\n" + extraIndent + taskList.get(index));
            } else {
                taskList.get(index).setIsDone(true);
                Ui.printMessageWithBorders("Nice! I've marked this task as done:\n" + extraIndent
                        + taskList.get(index));
            }
        } else {
            if (!taskList.get(index).getIsDone()) {
                Ui.printMessageWithBorders("Task is already not marked as done:\n" + extraIndent + taskList.get(index));
            } else {
                taskList.get(index).setIsDone(false);
                Ui.printMessageWithBorders("OK, I've marked this task as not done yet:\n" + extraIndent
                        + taskList.get(index));
            }
        }
        LocalSave.saveToFile(saveFilePath, TaskSerializer.serializeTasks(taskList));
    }

    /**
     * Deletes a task from the task list based on the provided index.
     *
     * @param index The index of the task in the task list (0-based).
     */
    public void deleteTask(int index) {
        String indent = Ui.getIndent(); // 4 spaces for normal indentation
        String extraIndent = Ui.getExtraIndent();
        if (index < 0 || index >= taskList.size()) {
            Ui.printMessageWithBorders("Oi! That task number doesn't even exist. Are you trying to trick me?");
            return;
        }
        Task removedTask = taskList.remove(index);
        Ui.printMessageWithBorders("Alright, I've erased this task from existence:\n" + extraIndent
                + removedTask + "\n" + indent + "Now you have " + taskList.size() + " task(s) left in the list.");
        LocalSave.saveToFile(saveFilePath, TaskSerializer.serializeTasks(taskList));
    }

    /**
     * Finds and prints tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     */
    public void findTasks(String keyword) {
        String indent = Ui.getIndent();
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.isEmpty()) {
            Ui.printMessageWithBorders("No tasks found matching: \"" + keyword + "\"");
            return;
        }
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            result.append(indent)
                  .append(i + 1)
                  .append(". ")
                  .append(foundTasks.get(i).toString())
                  .append("\n");
        }
        Ui.printMessageWithBorders(result.toString().trim());
    }
}
