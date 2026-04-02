package Nova.ui;

import Nova.exception.NovaException;
import Nova.task.Task;
import Nova.task.TaskList;

import java.util.Scanner;

/**
 * Handles all user interactions in the Nova application.
 * <p>
 * Responsibilities include:
 * - Reading user input
 * - Displaying messages, task lists, errors, and welcome/bye messages
 */
public class TextUi {
    private final Scanner scanner;

    /**
     * Constructs a TextUi instance and initializes the input scanner.
     */
    public TextUi() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a line separator to improve readability of messages.
     */
    public void showLineSeparator() {
        System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
    }

    /**
     * Displays a message wrapped by line separators.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        showLineSeparator();
        System.out.println(message);
        showLineSeparator();
    }

    /**
     * Displays a message along with a task, wrapped by line separators.
     *
     * @param message the message to display
     * @param task the task to show
     */
    public void showMessage(String message, Task task) {
        showLineSeparator();
        System.out.println(message);
        System.out.println("    " + task);
        showLineSeparator();
    }

    /**
     * Displays the welcome message when the program starts.
     */
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


    /**
     * Displays the goodbye message when the program exits.
     */
    public void showByeMessage() {
        showMessage(" Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked
     */
    public void showMarkedTask(Task task) {
        showMessage(" Nice! I've marked this task as done:", task);
    }


    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task the task that was unmarked
     */
    public void showUnmarkedTask(Task task) {
        showMessage(" OK, I've marked this task as not done yet:", task);
    }

    /**
     * Displays a message indicating that a task has been added into task list.
     *
     * @param task the task that was added
     * @param tasksSize the current number of tasks in the list
     */
    public void showAddedTask(Task task, int tasksSize) {
        showLineSeparator();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasksSize + " tasks in the list.");
        showLineSeparator();
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param task the task that was deleted
     * @param tasksSize the current number of tasks in the list
     */
    public void showDeletedTask(Task task, int tasksSize) {
        showLineSeparator();
        System.out.println(" Got it. I've deleted this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasksSize + " tasks in the list.");
        showLineSeparator();
    }

    /**
     * Displays an error message to the user when error encountered.
     *
     * @param message the error message
     */
    public void showError(String message) {
        showLineSeparator();
        System.out.println(" OOPS!!! " + message);
        showLineSeparator();
    }

    /**
     * Displays all tasks that match a search keyword.
     *
     * @param matchingTasks the task list that match the keyword
     * @param keyword the search keyword
     * @throws NovaException If accessing tasks fails (getTask(taskIndex))
     */
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

    /**
     * Reads a line of input from the user and trims whitespace
     * after user pressed enter button.
     *
     * @return the trimmed input string
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }
}
