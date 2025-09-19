package arpa.home.yikjin.app.kuro.ui;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import arpa.home.yikjin.app.kuro.exception.base.AppException;
import arpa.home.yikjin.app.kuro.task.Task;

public final class Ui {
    private static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);

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

    public static void addedTask(final Task task, final int numTasks) {
        System.out.println("Got it. I've added this task:");

        System.out.printf("  %s", task);
        System.out.println();

        tasksCount(numTasks);
    }

    public static void tasksCount(final int numTasks) {
        System.out.printf("Now you have %d tasks in the list.", numTasks);
        System.out.println();
    }

    public static void removeTaskBegin() {
        System.out.println("Noted. I've removed this task:");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void errException(final AppException message) {
        System.out.println(message.toString());
    }

    public static void listTasksBegin() {
        System.out.println("Here are the tasks in your list:");
    }

    public static void listTaskDetails(final int listNumber, final Task task) {
        System.out.printf("%d. %s", listNumber, task);
        System.out.println();
    }

    public static void markTasksBegin() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public static void unmarkTasksBegin() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public static String getUserPrompt() {
        System.out.println();
        System.out.print("> ");

        return SCANNER.nextLine();
    }

    public static void close() {
        SCANNER.close();
    }

    @Override
    public String toString() {
        return SCANNER.toString();
    }
}
