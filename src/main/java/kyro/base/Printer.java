package kyro.base;

import kyro.tasks.*;

import java.util.ArrayList;

/**
 * Handles all print output in the Kyro application.
 * <p>
 * Provides helper methods for printing messages, errors, and task lists.
 */
public class Printer {

    private static final String SINGLE_LINE = "____________________________________________________________";

    public void showLine() {
        System.out.println(SINGLE_LINE);
    }

    public void showWelcome() {
        String logo = " ____  __.                      \n"
                + "|    |/ _|___.__._______  ____  \n"
                + "|      < <   |  |\\_  __ \\/  _ \\ \n"
                + "|    |  \\ \\___  | |  | \\(  <_> )\n"
                + "|____|__ \\/ ____| |__|   \\____/ \n"
                + "        \\/\\/                     ";
        System.out.println("************************ HELLO FROM ************************\n" + logo);
        showLine();
        System.out.println(" Hello! I'm Kyro, your personal assistant.");
        System.out.println(" What can Kyro do for you?");
        showHelp();
        showLine();
    }

    public void showBye() {
        System.out.println(" Bye. Kyro hopes to see you again soon!");
    }

    public void showTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" Kyro is glad that you have no tasks!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf(" %d. %s%n", i + 1, tasks.get(i));
            }
        }
    }

    public void showAdded(Task task, int count) {
        System.out.println(" Kyro has added:\n  " + task);
        System.out.println(" Now you have " + count + " tasks in the list!");
    }

    public void showMark(Task task) {
        System.out.println(" Nice! Kyro has marked this task as done:");
        System.out.println("   " + task);
    }

    public void showUnmark(Task task) {
        System.out.println(" Ok, Kyro has marked this task as not done yet:");
        System.out.println("   " + task);
    }

    public void showDelete(Task task, int count) {
        System.out.println(" Kyro has deleted:\n  " + task);
        System.out.println(" Now you have " + count + " tasks in the list!");
    }

    public void showFind(ArrayList<Task> matchingTasks) {
        System.out.println(" Here are the matching tasks in your schedule:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.printf(" %d. %s%n", i + 1, matchingTasks.get(i));
        }
    }

    public void showHelp() {
        System.out.println("************************* COMMANDS *************************\n" +
                "todo <description>                                                --> Add a todo Task\n" +
                "deadline <description> /by <yyyy-MM-dd HHmm>                      --> Add a deadline Task\n" +
                "event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm> --> Add an event Task\n" +
                "list                                                              --> List out all tasks\n" +
                "mark <task number>                                                --> Mark task by number\n" +
                "unmark <task number>                                              --> Unmark task by number\n" +
                "delete <task number>                                              --> Delete task by number\n" +
                "filter <yyyy-MM-dd>                                               --> Filter by date\n" +
                "find <keyword>                                                    --> Find by keyword\n" +
                "help                                                              --> Show this help message\n" +
                "bye                                                               --> End Session"
        );
    }

    public void showError(String message) {
        System.out.println(" " + message);
    }
}
