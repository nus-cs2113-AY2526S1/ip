package ui;

import java.util.ArrayList;
import java.util.Scanner;
import task.Task;
import task.TaskList;

/**
 * Handles all interactions with the user.
 * Responsible for displaying messages, reading input, and formatting output.
 */
public class Ui {
    private static final String logo =

            "████████╗ ██╗    ██╗ ██╗ ███╗   ██╗\n" +
                    "╚══██╔══╝ ██║    ██║ ██║ ████╗  ██║\n" +
                    "   ██║    ██║ █╗ ██║ ██║ ██╔██╗ ██║\n" +
                    "   ██║    ██║███╗██║ ██║ ██║╚██╗██║\n" +
                    "   ██║    ╚███╔███╔╝ ██║ ██║ ╚████║\n" +
                    "   ╚═╝     ╚══╝╚══╝  ╚═╝ ╚═╝  ╚═══╝\n";

    private Scanner scanner;

    /**
     * Constructs a Ui object with a single Scanner instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in); // single scanner instance
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcome() {
        printBox("Hello from\n" + logo + "Hello I am Twin\nNice to meet you!\nWhat can I do for you?");
    }

    /**
     * Displays an error message in a formatted box.
     * @param error the error message to show
     */
    public void showError(String error) {
        printBox(error);
    }

    /**
     * Shows a dividing line in the console for readability.
     */
    public void showLine() {
        System.out.println("-------------------------------------------------------------------------------");
    }

    /**
     * Displays all tasks that match a search keyword.
     * @param matchedTasks list of tasks that match the keyword
     */
    public void showFoundTasks(ArrayList<Task> matchedTasks) {
        printBox("Here are the matching tasks in your list:");
        int count = 1;
        for (Task t : matchedTasks) {
            printBox(count + ". " + t);
            count++;
        }
    }

    /**
     * Reads a line of user input from the console.
     * @return trimmed user input as a String
     */
    public String readCommand() {
        System.out.println();
        return scanner.nextLine().trim();
    }

    /**
     * Displays all tasks in the TaskList.
     * @param tasks the TaskList to display
     */
    public void showTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            printBox("Your task list is empty!");
            return;
        }
        int count = 1;
        for (Task t : tasks.getAllTasks()) {
            printBox(count + ". " + t.getStatusIcon());
            count++;
        }
    }

    /**
     * Displays a message for a task that has been marked as done.
     * @param taskMarked the task that was marked
     */
    public void showMarkedTask(Task taskMarked) {
        printBox("Nice! I've marked this task as done:\n" + taskMarked);
    }

    /**
     * Displays a message for a task that has been unmarked.
     * @param taskUnMarked the task that was unmarked
     */
    public void showUnMarkedTask(Task taskUnMarked) {
        printBox("OK, I've marked this task as not done yet:\n" + taskUnMarked);
    }

    /**
     * Displays a message for a newly added deadline task.
     * @param deadline the deadline task added
     */
    public void showAddedDeadline(Task deadline) {
        printBox("Got it. I have added this task:\n" + deadline);
    }

    /**
     * Displays a message for a newly added todo task.
     * @param todo the todo task added
     */
    public void showAddedTodo(Task todo) {
        printBox("Got it. I have added this task:\n" + todo);
    }

    /**
     * Displays a message for a newly added event task.
     * @param event the event task added
     */
    public void showAddedEvent(Task event) {
        printBox("Got it. I have added this task:\n" + event);
    }

    /**
     * Displays a message when a task is deleted.
     * @param removedTask the task removed
     * @param listSize the current number of tasks remaining
     */
    public void showDeletedTask(Task removedTask, int listSize) {
        printBox("Noted. I have removed this task:\n" + removedTask +
                "\nNow you have " + listSize + " tasks in the list.");
    }

    /**
     * Displays a goodbye message when the program exits.
     */
    public void showGoodbye() {
        printBox("Goodbye! Hope to see you again soon!");
    }

    /**
     * Utility method to print a message inside a box of dashes.
     * @param message the message to print
     */
    public static void printBox(String message) {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(message);
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
