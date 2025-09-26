package Nova.ui;

import Nova.exception.NovaException;
import Nova.task.Task;
import Nova.task.TaskList;

import java.util.Scanner;

public class TextUi {
    private final Scanner scanner;

    public TextUi() {
        scanner = new Scanner(System.in);
    }

    public void showLineSeparator() {
        System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
    }

    public void showMessage(String message) {
        showLineSeparator();
        System.out.println(message);
        showLineSeparator();
    }

    public void showMessage(String message, Task task) {
        showLineSeparator();
        System.out.println(message);
        System.out.println("    " + task);
        showLineSeparator();
    }

    public void showWelcomeMessage() {
        showLineSeparator();
        System.out.print("""
                  _   _   ____  __      __    _
                 | \\ | | / __ \\ \\ \\    / /   / \\
                 |  \\| || |  | | \\ \\  / /   / _ \\
                 | |\\  || |  | |  \\ \\/ /   / ___ \\
                 |_| \\_| \\____/    \\__/   /_/   \\_\\
                
                 Hello! I'm Nova
                 What can I do for you?
                """);
        showLineSeparator();
    }

    public void showByeMessage() {
        showMessage(" Bye. Hope to see you again soon!");
    }

    public void showMarkedTask(Task task) {
        showMessage(" Nice! I've marked this task as done:", task);
    }

    public void showUnmarkedTask(Task task) {
        showMessage(" OK, I've marked this task as not done yet:", task);
    }

    public void showAddedTask(Task task, int tasksSize) {
        showLineSeparator();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasksSize + " tasks in the list.");
        showLineSeparator();
    }

    public void showDeletedTask(Task task, int tasksSize) {
        showLineSeparator();
        System.out.println(" Got it. I've deleted this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasksSize + " tasks in the list.");
        showLineSeparator();
    }

    public void showError(String message) {
        showLineSeparator();
        System.out.println(" OOPS!!! " + message);
        showLineSeparator();
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showAllTasks(TaskList matchingTasks, String keyword) throws NovaException {
        if (matchingTasks.isEmpty()) {
            showMessage(" No matching tasks found that matches: " + keyword );
        } else {
            showLineSeparator();
            System.out.println(" Search: " + keyword);
            System.out.println(" Here are matching tasks in your list:");
            for (int taskIndex = 1; taskIndex <= matchingTasks.getTasksSize(); taskIndex++) {
                Task task = matchingTasks.getTask(taskIndex);
                System.out.println(" " + (taskIndex) + ". " + task);
            }
            showLineSeparator();
        }
    }
}
