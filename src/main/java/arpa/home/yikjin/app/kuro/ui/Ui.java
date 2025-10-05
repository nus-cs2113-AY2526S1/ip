package arpa.home.yikjin.app.kuro.ui;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import arpa.home.yikjin.app.kuro.exception.base.AppException;
import arpa.home.yikjin.app.kuro.task.Task;

/**
 * User interface methods for printing out predefined messages to standard output
 */
public final class Ui {
    /**
     * Scanner to read in user input via {@code System.in}
     */
    private static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);

    /**
     * Print the Kuro logo and greet the user
     */
    public static void greet() {
        final String nameLogo = """
                ██╗  ██╗██╗   ██╗██████╗  ██████╗
                ██║ ██╔╝██║   ██║██╔══██╗██╔═══██╗
                █████╔╝ ██║   ██║██████╔╝██║   ██║
                ██╔═██╗ ██║   ██║██╔══██╗██║   ██║
                ██║  ██╗╚██████╔╝██║  ██║╚██████╔╝
                ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝ ╚═════╝""";

        System.out.println("Hello! I'm");
        System.out.println(nameLogo);
        System.out.println();

        System.out.println("What can I do for you?");
    }

    /**
     * Print successfully added task
     *
     * @param task     Task that has been added successfully
     * @param numTasks Number used to refer to this task
     */
    public static void addedTask(final Task task, final int numTasks) {
        System.out.println("Got it. I've added this task:");

        System.out.printf("  %s", task);
        System.out.println();

        tasksCount(numTasks);
    }

    /**
     * Print the total number of tasks in the list
     *
     * @param numTasks Total number of tasks
     */
    public static void tasksCount(final int numTasks) {
        System.out.printf("Now you have %d tasks in the list.", numTasks);
        System.out.println();
    }

    /**
     * Print the heading when removing a task
     */
    public static void removeTaskBegin() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Print the goodbye message
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print the exception message
     *
     * @param message Exception that occurred
     */
    public static void errException(final AppException message) {
        System.out.println(message.toString());
    }

    /**
     * Print the heading when listing all tasks in list
     */
    public static void listTasksBegin() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Print the task details of a task in the list
     *
     * @param listNumber Number used to refer to the task
     * @param task       The task in the list
     */
    public static void listTaskDetails(final int listNumber, final Task task) {
        System.out.printf("%d. %s", listNumber, task);
        System.out.println();
    }

    /**
     * Print the heading when marking a task as done
     */
    public static void markTasksBegin() {
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Print the heading when unmarking a task
     */
    public static void unmarkTasksBegin() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /**
     * Print the prompt and get the next user input
     *
     * @return The next line of command entered by the user
     */
    public static String getUserPrompt() {
        System.out.println();
        System.out.print("> ");

        return SCANNER.nextLine();
    }

    /**
     * Close standard input
     */
    public static void close() {
        SCANNER.close();
    }

    /**
     * Convert class instance to a {@code String} representation
     *
     * @return The {@code String} representation
     */
    @Override
    public String toString() {
        return SCANNER.toString();
    }
}
