import java.util.Scanner;

public class Nova {
    private static final int maxTaskCount = 100;
    private static final Task[] tasks = new Task[maxTaskCount];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine().trim();
            String[] userInputArray = userInput.split(" ", 2);
            String command = userInputArray[0];

            switch (command) {
            case "bye":
                printByeMessage();
                return;
            case "list":
                printAllTask();
                break;
            case "mark":
                if (userInputArray.length < 2) {
                    printMarkError();
                } else {
                    handleMark(userInputArray[1], true);
                }
                break;
            case "unmark":
                if (userInputArray.length < 2) {
                    printMarkError();
                } else {
                    handleMark(userInputArray[1], false);
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                addTask(command, userInputArray.length > 1 ? userInputArray[1] : "");
                break;
            default:
                printInvalidError();
            }

        }
    }


    private static void printLineSeparator() {
        System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
    }


    private static void printWelcomeMessage() {
        printLineSeparator();
        System.out.print("""
                  _   _   ____  __      __    _
                 | \\ | | / __ \\ \\ \\    / /   / \\
                 |  \\| || |  | | \\ \\  / /   / _ \\
                 | |\\  || |  | |  \\ \\/ /   / ___ \\
                 |_| \\_| \\____/    \\__/   /_/   \\_\\
                
                 Hello! I'm Nova
                 What can I do for you?
                """);
        printLineSeparator();
    }

    private static void printByeMessage() {
        printLineSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    public static void printTaskAdded(Task task, int taskCount) {
        printLineSeparator();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        printLineSeparator();
    }

    public static void printAllTask() {
        printLineSeparator();
        if  (taskCount == 0) {
            System.out.println(" No tasks in this list.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks[i].toString());
            }
        }
        printLineSeparator();
    }

    private static void handleMark(String userInput, boolean done) {
        try {
            int taskNumber = Integer.parseInt(userInput);

            checkMarkNumber(taskNumber);

            Task currentTask = tasks[taskNumber - 1];
            printLineSeparator();
            if (done) {
                currentTask.markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
            } else {
                currentTask.markAsNotDone();
                System.out.println(" OK, I've marked this task as not done yet:");
            }
            System.out.println("    " + currentTask);
            printLineSeparator();
        } catch (NumberFormatException e) {
            printLineSeparator();
            System.out.println(" Invalid task number!");
            printLineSeparator();
        } catch (IllegalArgumentException e) {
            printLineSeparator();
            System.out.println(" " + e.getMessage());
            printLineSeparator();
        }
    }

    private static void printMarkError() {
        printLineSeparator();
        System.out.println(" OOPS!!! You must specify a task number to unmark/unmark.");
        printLineSeparator();
    }

    private static void addTask(String command, String userInput) {
        Task newTask = null;

        switch (command) {
        case "todo":
            if (userInput.isEmpty()) {
                printWrongTodoFormatError();
                return;
            }
            newTask = new Todo(userInput);
            break;
        case "deadline":
            try {
                String[] userInputArray = userInput.split("/by", 2);
                String description =  userInputArray[0].trim();
                String by = userInputArray[1].trim();
                newTask = new Deadline(description, by);
            } catch (Exception e) {
                printWrongDeadlineFormatError();
            }
            break;
        case "event":
            try {
                String[] userInputArray = userInput.split("/from|/to");
                String description = userInputArray[0].trim();
                String from = userInputArray[1].trim();
                String to = userInputArray[2].trim();
                newTask = new Event(description, from, to);
            } catch (Exception e) {
                printWrongEventFormatError();
            }
            break;
        }
        if (newTask != null) {
            if (taskCount >= maxTaskCount) {
                printTaskFull();
                return;
            }
            tasks[taskCount++] = newTask;
            printTaskAdded(newTask, taskCount);
        }
    }

    private static void checkMarkNumber(int taskNumber) {
        if (taskCount == 0) {
            throw new IllegalArgumentException("List is empty, no tasks to mark!");
        }
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new IllegalArgumentException("Invalid task number! Please enter a number from 1 to " + taskCount + ".");
        }
    }

    public static void printTaskFull() {
        printLineSeparator();
        System.out.println(" You have reached the limit of " + maxTaskCount + " tasks! Unable to add more tasks!");
        printLineSeparator();
    }

    private static void printWrongEventFormatError() {
        printLineSeparator();
        System.out.println(" OOPS!!! Please use the format: event <desc> /from <start> /to <end>");
        printLineSeparator();
    }

    private static void printWrongDeadlineFormatError() {
        printLineSeparator();
        System.out.println(" OOPS!!! Please use the format: deadline <desc> /by <time>");
        printLineSeparator();
    }

    private static void printInvalidError() {
        printLineSeparator();
        System.out.println(" OOPS! I don't understand that command.");
        printLineSeparator();
    }

    private static void printWrongTodoFormatError() {
        printLineSeparator();
        System.out.println(" OOPS!!! Please use the format: todo <desc>");
        printLineSeparator();
    }
}

