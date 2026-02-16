package ui;

import java.util.List;

import exceptions.PepException;
import tasks.Task;
import tasks.TaskList;

/**
 * Handles all user interface interactions, including displaying messages
 * and task lists to the user.
 */
public class Ui {
    private final String line;

    public Ui() {
        this.line = "____________________________________________________________";
    }

    /**
     * Displays the welcome message when the chatbot starts.
     *
     * @param chatbotName the name of the chatbot
     */
    public void showWelcome(String chatbotName) {
        printLine();
        System.out.println(" Hi im" + chatbotName);
        System.out.println(" What you want");
        printLine();
    }

    public void showAdded(String task,  int totalTasks) {
        printLine();
        System.out.println(" added: " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }

    public void showTaskList(TaskList taskList) throws PepException {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.getCount(); i++) {
            Task task = taskList.getTask(i);
            System.out.println(" " + (i + 1) + task);
        }
        printLine();
    }

    /**
     * Displays the given list of tasks with numbering.
     *
     * @param tasks the list of tasks to display
     */
    public void showTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("____________________________________________________________");
            System.out.println(" No matching tasks found.");
            System.out.println("____________________________________________________________");
            return;
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void showMarked(Task task) {
        printLine();
        System.out.println(" task marked as done:");
        System.out.println("   " + task);
        printLine();
    }

    public void showUnmarked(Task task) {
        printLine();
        System.out.println(" this task marked as not done:");
        System.out.println("   " + task);
        printLine();
    }

    public void showGoodbye() {
        printLine();
        System.out.println(" Bye. please dont come back");
        printLine();
    }
    public void showDeleted(Task task, int remainingCount) {
        printLine();
        System.out.println(" hAhahah tASk has been yEETED:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + remainingCount + " tasks in the list.");
        printLine();
    }

    private void printLine() {
        System.out.println(line);
    }

    /**
     * Displays an error message to the user in a consistent boxed format.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }

    public void showMessage(String s) {
        System.out.println(s);
    }

}
