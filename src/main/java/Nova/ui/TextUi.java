package Nova.ui;

import Nova.task.Task;

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

    public void showLoadingError(String message) {
        showError("Unable to load saved tasks. Starting with an empty list." + message);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }
}
