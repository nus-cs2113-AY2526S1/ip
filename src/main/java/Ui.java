import java.nio.charset.StandardCharsets;
import java.util.Scanner;

final class Ui {
    private static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);

    static void greet() {
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

    static void addedTask(final Task task, final int numTasks) {
        System.out.println("Got it. I've added this task:");

        System.out.printf("  %s", task);
        System.out.println();

        System.out.printf("Now you have %d tasks in the list.", numTasks);
        System.out.println();
    }

    static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void errException(final AppException message) {
        System.out.println(message.toString());
    }

    static void listTasksBegin() {
        System.out.println("Here are the tasks in your list:");
    }

    static void listTaskDetails(final int listNumber, final Task task) {
        System.out.printf("%d. %s", listNumber, task);
        System.out.println();
    }

    static void markTasksBegin() {
        System.out.println("Nice! I've marked this task as done:");
    }

    static void unmarkTasksBegin() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    static String getUserPrompt() {
        System.out.println();
        System.out.print("> ");

        return SCANNER.nextLine();
    }

    static void close() {
        SCANNER.close();
    }

    @Override
    public String toString() {
        return SCANNER.toString();
    }
}
