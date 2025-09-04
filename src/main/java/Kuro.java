import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

final class Kuro {
    private static final int TASKS_INIT_SIZE = 10;
    private static final ArrayList<Task> tasks = new ArrayList<>(Kuro.TASKS_INIT_SIZE);

    public static void main(final String[] args) {
        Kuro.greet();
        Kuro.startChatSession();
    }

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

    private static void startChatSession() {
        try (final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            do {
                System.out.println();
                System.out.print("> ");

                final String inputLine = scanner.nextLine().strip();
                final String[] inputTokens = inputLine.split(" ", 2);
                final String inputCommand = inputTokens.length > 0 ? inputTokens[0] : "";
                final String[] inputArguments = inputTokens.length > 1
                        ? inputTokens[1].split(" ")
                        : new String[] {};

                switch (inputCommand) {
                case "bye":
                    Kuro.quit();
                    return;  // Exit the chat session
                case "list":
                    Kuro.listTasks();
                    break;
                case "mark":
                    Kuro.setTasksDone(inputArguments);
                    break;
                case "unmark":
                    Kuro.setTasksDone(inputArguments, false);
                    break;
                default:  // Add a task
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

    private static void listTasks() {
        if (Kuro.tasks.isEmpty()) {
            System.out.println("error: no tasks added!");
            return;
        }

        final int numTasks = Kuro.tasks.size();
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < numTasks; i++) {
            final int listNumber = i + 1;
            final Task task = Kuro.tasks.get(i);

            System.out.printf("%d. [%c] %s", listNumber, task.getStatusIcon(), task.getName());
            System.out.println();
        }
    }

    private static void setTasksDone(final String[] taskIds) {
        Kuro.setTasksDone(taskIds, true);
    }

    private static void setTasksDone(final String[] taskIds, final boolean isDone) {
        if (taskIds.length < 1) {
            System.out.printf("usage: %s [taskId]...", isDone ? "mark" : "unmark");
            System.out.println();
            return;
        }

        System.out.println(isDone
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:");

        for (final String taskIdString : taskIds) {
            final int taskId;

            try {
                taskId = Integer.parseInt(taskIdString);
            } catch (final NumberFormatException ignored) {
                System.out.printf("error: '%s' is not an integer!", taskIdString);
                System.out.println();
                continue;
            }

            if (taskId < 1 || taskId > Kuro.tasks.size()) {
                System.out.printf("error: task id %d is invalid!", taskId);
                System.out.println();
                continue;
            }

            final Task task = Kuro.tasks.get(taskId - 1);
            task.setDone(isDone);

            System.out.printf("[%c] %s", task.getStatusIcon(), task.getName());
            System.out.println();
        }
    }

    private static void addTask(final String taskName) {
        final Task task = new Task(taskName);

        Kuro.tasks.add(task);

        System.out.printf("added: %s", task.getName());
        System.out.println();
    }
}
