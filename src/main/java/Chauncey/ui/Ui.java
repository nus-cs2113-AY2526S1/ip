package Chauncey.ui;

import Chauncey.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Chauncey.");
        System.out.println("List of things I can do: list / add / delete / mark / unmark / find. If you want to exit, please input \"bye\".");
        System.out.println("What can I do for you?");
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingMessage() {
        System.out.println("Loading from previous data...");
    }

    public void showLoadingError() {
        System.out.println("No previous data found.");
    }

    public void showDateFormatError() {
        System.out.println("Please input date in this format: yyyy-MM-dd HHmm");
    }

    public void showErrorMessage(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    public void showSelectTaskTypeMessage() {
        System.out.print("What type of task do you want to add? todo/deadline/event?");
    }

    public void showInputTaskDetailsMessage() {
        System.out.println("Please enter the task details (split details by '/'): ");
        System.out.println("(For deadline details: input <description> / by <yyyy-MM-dd HHmm>.\nFor event details: input " +
                "<description> / from <yyyy-MM-dd HHmm> / to <yyyy-MM-dd HHmm>.)");
    }

    public void showTaskAddedMessage(Task task, int numOfTasks) {
        System.out.println("Got it. I've added this task: ");
        task.outputTaskDetails();
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void showTaskDeletedMessage(String taskDetails, int numOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskDetails);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i<= tasks.getSize(); i++) {
            System.out.print(i + ".");
            tasks.getTask(i-1).outputTaskDetails();
        }
    }

    public void listFilteredTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i<= tasks.getSize(); i++) {
            System.out.print(i + ".");
            tasks.getTask(i-1).outputTaskDetails();
        }
    }

    public void showMarkTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        task.outputTaskDetails();
    }

    public void showUnmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        task.outputTaskDetails();
    }

    public void askForNextCommand() {
        System.out.println("Next command?");
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
