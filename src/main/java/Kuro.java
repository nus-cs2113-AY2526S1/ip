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

        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < Kuro.latestTaskIndex; i++) {
            final int listNumber = i + 1;
            final Task task = Kuro.tasks[i];

            System.out.printf("%d.[%c] %s", listNumber, task.getStatusIcon(), task.getName());
            System.out.println();
        }
    }

    private static void setTaskDone(final String[] taskIds) {
        if (taskIds.length < 1) {
            System.out.println("usage: mark [taskId]...");
            return;
        }

        System.out.println("Nice! I've marked this task as done:");

        for (final String taskIdString : taskIds) {
            final int taskId;

            try {
                taskId = Integer.parseInt(taskIdString);
            } catch (final NumberFormatException ignored) {
                System.out.printf("error: '%s' is not an integer!", taskIdString);
                System.out.println();
                continue;
            }

            if (taskId < 1 || taskId > Kuro.latestTaskIndex) {
                System.out.printf("error: task id %d is invalid!", taskId);
                System.out.println();
                continue;
            }

            final Task task = Kuro.tasks[taskId - 1];
            task.setDone(true);

            System.out.printf("[%c] %s", task.getStatusIcon(), task.getName());
            System.out.println();
        }
    }

    private static void setTaskUndone(final String[] taskIds) {
        if (taskIds.length < 1) {
            System.out.println("usage: unmark [taskId]...");
            return;
        }

        System.out.println("OK, I've marked this task as not done yet:");

        for (final String taskIdString : taskIds) {
            final int taskId;

            try {
                taskId = Integer.parseInt(taskIdString);
            } catch (final NumberFormatException ignored) {
                System.out.printf("error: '%s' is not an integer!", taskIdString);
                System.out.println();
                continue;
            }

            if (taskId < 1 || taskId > Kuro.latestTaskIndex) {
                System.out.printf("error: task id %d is invalid!", taskId);
                System.out.println();
                continue;
            }

            final Task task = Kuro.tasks[taskId - 1];
            task.setDone(false);

            System.out.printf("[%c] %s", task.getStatusIcon(), task.getName());
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
                final String[] inputTokens = inputLine.split(" ", 2);
                final String inputCommand = inputTokens.length > 0 ? inputTokens[0] : "";
                final String[] inputArguments = inputTokens.length > 1
                        ? inputTokens[1].split(" ")
                        : new String[] {};

                switch (inputCommand) {
                case "bye":
                    // Exit the chat session
                    return;
                case "list":
                    // List all stored tasks
                    Kuro.listTasks();
                    break;
                case "mark":
                    Kuro.setTaskDone(inputArguments);
                    break;
                case "unmark":
                    Kuro.setTaskUndone(inputArguments);
                    break;
                default:
                    // Add a task
                    Kuro.addTask(inputCommand);
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
