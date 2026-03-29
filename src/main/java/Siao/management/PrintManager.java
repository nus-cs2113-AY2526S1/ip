package Siao.management;

import Siao.UI.Siao;
import Siao.task.Task;

import java.util.ArrayList;

/**
 * Handles all user-facing console output for task-related actions.
 * <p>
 * This utility class provides methods to display task lists, notifications
 * for adding, deleting, marking tasks as done/undone, and general formatting
 * such as divider lines and task counters.
 * <p>
 * All methods in this class are static and intended to centralize printing logic
 * to ensure consistent formatting across the application.
 */
public class PrintManager {

    /**
     * Prints the list of tasks with index numbering.
     * If the list is empty, displays an appropriate message.
     *
     * @param list the list of tasks to be printed
     */
    public static void printList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println(Constants.EMPTY_LIST);
            return;
        }

        printDividerLine();
        int indexNumber = 1;
        for (Task task : list) {
            System.out.printf("%d. %s",indexNumber, task.toString());
            indexNumber++;
        }
        System.out.println(Constants.END_OF_LIST);

    }

    public static void printAddedTask(Task task, ArrayList<Task> list) {
        printDividerLine();
        System.out.println("Got it. I've added this task!");
        System.out.print("   " + task.toString());
        System.out.print(printNumberOfTasks(list));
        printDividerLine();
    }

    public static void printDeletedTask(ArrayList<Task> list, int taskIndex) {
        if (list.isEmpty()) {
            System.out.println(Constants.EMPTY_LIST);
        } else {
            printDividerLine();
            System.out.println("Got it. I've deleted this task!");
            System.out.printf("  " + list.get(taskIndex).toString());
            list.remove(taskIndex);
            System.out.print(printNumberOfTasks(list));
            printDividerLine();
        }
    }

    public static void printMarkDone(Task task) {
        PrintManager.printDividerLine();
        System.out.println("Good job on completing the task!");
        System.out.print("  ");
        System.out.printf(task.toString());
        PrintManager.printDividerLine();
    }

    public static void printMarkUndone(Task task) {
        PrintManager.printDividerLine();
        System.out.println("Task is not done yet. Why you unmark?");
        System.out.print("  ");
        System.out.printf(task.toString());
        PrintManager.printDividerLine();
    }

    public static void  printDividerLine(){
        System.out.println(Constants.DIVIDER);
    }

    /**
     * Returns a formatted message indicating the current total number of tasks.
     *
     * @param task any task instance (used only to reference the task counter)
     * @return a formatted task counter message
     */
    public static String printNumberOfTasks(ArrayList<Task> list) {
        return String.format("Now you have %d task(s) in the list\n", list.size());
    }

}
