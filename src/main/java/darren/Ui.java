package darren;

import darren.task.Task;
import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    public static final String LINE = "____________________________________________________________";
    public static final String LOGO = "Darren";

    /**
     * Displays the welcome message upon startup.
     */
    public void displayWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + LOGO);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Reads the next line of user input.
     * @return The user's raw input string.
     */
    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Displays the relevant error message.
     * @param message The error message to be displayed.
     */
    public void displayError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Displays a specific error message when loading tasks from the file fails.
     */
    public void showLoadingError() {
        displayError("Error loading tasks from file. Starting with an empty list.");
    }

    /**
     * Displays the full list of tasks.
     * @param tasks The TaskList to be displayed.
     */
    public void displayList(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println(LINE);
    }

    /**
     * Displays a message when a task is marked as done.
     * @param t The task that has been marked as done.
     */
    public void displayMark(Task t) {
        System.out.println(LINE);
        System.out.println("Good work! This task has been completed:");
        System.out.println("    " + t);
        System.out.println(LINE);
    }

    /**
     * Displays a message when a task is marked as not done.
     * @param t The task that has been marked as not done.
     */
    public void displayUnmark(Task t) {
        System.out.println(LINE);
        System.out.println("Got it! I've marked this task as incomplete:");
        System.out.println("    " + t);
        System.out.println(LINE);
    }

    /**
     * Displays a message when a task is added to the list, and the updated list.
     * @param t The task that is newly added.
     * @param tasks The updated TaskList including the new task.
     */
    public void displayAdd(Task t, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Got it! I've added this task:");
        System.out.println("    " + t.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a message when a task is deleted from the list, and the updated list.
     * @param t The task that has been deleted,
     * @param tasks The updated TaskList after deletion.
     */
    public void displayDelete(Task t, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Noted! I've removed this task:");
        System.out.println("    " + t.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays the list of tasks that match the keyword.
     * @param matchingTasks The TaskList containing the tasks that contain the keyword.
     */
    public void displayFind(TaskList matchingTasks) {
        System.out.println(LINE);
        if (matchingTasks.size() == 0) {
            System.out.println("Sorry! No matching tasks were found in your list.");
            return;
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i).toString());
            }
        }
        System.out.println(LINE);
    }

    /**
     * Displays the exit message upon exiting the program.
     */
    public void displayBye() {
        System.out.println(LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Closes the scanner to prevent resource leaks.
     */
    public void close() {
        scanner.close();
    }
}