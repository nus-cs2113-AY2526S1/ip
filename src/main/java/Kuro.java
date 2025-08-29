import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

final class Kuro {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[Kuro.MAX_TASKS];
    private static int latestTaskIndex = 0;

    private static void greet() {
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

    private static void listTasks() {
        if (Kuro.latestTaskIndex == 0) {
            System.out.println("error: no tasks added!");
            return;
        }

        for (int i = 0; i < Kuro.latestTaskIndex; i++) {
            final int listNumber = i + 1;
            final String taskName = Kuro.tasks[i].getName();

            System.out.printf("%d. %s", listNumber, taskName);
            System.out.println();
        }
    }

    private static void addTask(final String taskName) {
        if (Kuro.latestTaskIndex >= Kuro.MAX_TASKS) {
            System.out.println("error: maximum task count reached!");
            return;
        }

        final Task task = new Task(taskName);

        Kuro.tasks[Kuro.latestTaskIndex] = task;
        Kuro.latestTaskIndex++;

        System.out.printf("added: %s", task.getName());
        System.out.println();
    }

    private static void startChatSession() {
        try (final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            do {
                final String inputLine = scanner.nextLine().strip();

                switch (inputLine) {
                case "bye":
                    // Exit the chat session
                    return;
                case "list":
                    // List all stored tasks
                    Kuro.listTasks();
                    break;
                default:
                    // Add a task
                    Kuro.addTask(inputLine);
                    break;
                }
            } while (true);
        } catch (final NoSuchElementException ignored) {
            // Ensure the code does not error out when passing in input via command line, and the input does not end
            // with "bye"
        }
    }

    private static void quit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(final String[] args) {
        Kuro.greet();
        Kuro.startChatSession();
        Kuro.quit();
    }
}
