package Nova.ui;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Deadline;
import Nova.task.Event;
import Nova.task.Task;
import Nova.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Nova {
    private static final String FilePath = "data/Nova.txt";
    private static final Storage storage = new Storage(FilePath);
    private static final ArrayList<Task> tasks = storage.loadTasks();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {
            try {
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
                        throw new NovaException(" OOPS!!! You must specify a task number to mark.");
                    }
                    handleMark(userInputArray[1], true);
                    break;
                case "unmark":
                    if (userInputArray.length < 2) {
                        throw new NovaException(" OOPS!!! You must specify a task number to unmark.");
                    }
                    handleMark(userInputArray[1], false);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    addTask(command, userInputArray.length > 1 ? userInputArray[1] : "");
                    break;
                case "delete":
                    if (userInputArray.length < 2) {
                        throw new NovaException(" OOPS!!! You must specify a task number to delete.");
                    }
                    deleteTask(userInputArray[1]);
                    break;
                default:
                    throw new NovaException("OOPS!!! I don't understand that command.");
                }
            } catch (NovaException e) {
                printLineSeparator();
                System.out.println(" " + e.getMessage());
                printLineSeparator();
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

    private static void printTaskDeleted(Task task, int taskCount) {
        printLineSeparator();
        System.out.println(" Got it. I've deleted this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        printLineSeparator();
    }

    public static void printAllTask() {
        printLineSeparator();
        if  (tasks.isEmpty()) {
            System.out.println(" No tasks in this list.");
        } else {
            System.out.println(" Number of tasks in this list: " + tasks.size());
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
            }
        }
        printLineSeparator();
    }

    private static void handleMark(String userInput, boolean done) throws NovaException {
        try {
            int taskNumber = Integer.parseInt(userInput);

            checkMarkNumber(taskNumber);

            Task currentTask = tasks.get(taskNumber - 1);
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

            storage.saveTasks(tasks);
        } catch (NumberFormatException e) {
            throw new NovaException(" Invalid task number format!");
        }
    }

    private static void deleteTask(String userInput) throws NovaException {
        try {
            int index = Integer.parseInt(userInput);
            if (index < 1 || index > tasks.size()) {
                throw new NovaException(" OOPS!!! Invalid task number! Please enter a number between 1 and " + tasks.size());
            }
            Task taskRemoved = tasks.remove(index - 1);
            printTaskDeleted(taskRemoved, tasks.size());

        } catch (NumberFormatException e) {
            throw new NovaException(" OOPS!!! Invalid task number format!");
        }
    }

    private static void addTask(String command, String userInput) throws NovaException {
        Task newTask = null;

        switch (command) {
        case "todo":
            if (userInput.isEmpty()) {
                throw new NovaException(" OOPS!!! Please use the format: todo <desc>");
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
                throw new NovaException(" OOPS!!! Please use the format: deadline <desc> /by <time>");
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
                throw new NovaException("OOPS!!! Please use the format: event <desc> /from <start> /to <end>");
            }
            break;
        }
        if (newTask != null) {
            tasks.add(newTask);
            printTaskAdded(newTask, tasks.size());
            storage.saveTasks(tasks);
        }
    }

    private static void checkMarkNumber(int taskNumber) throws NovaException {
        if (tasks.isEmpty()) {
            throw new NovaException("List is empty, no tasks to mark!");
        }
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new NovaException("Invalid task number! Please enter a number from 1 to " + tasks.size() + ".");
        }
    }
}

