package tarnia;

import java.util.ArrayList;

/**
 * Handles all user interface messages and outputs for the Tarnia application.
 */
public class Ui {

    /**
     * Prints the welcome message when the application starts.
     */
    public void printHelloMessage() {
        System.out.println("Hello! I'm TARNIA 💔");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a message when a new task is added.
     *
     * @param task The task that was added.
     */
    public void printAddTaskMessage(Task task) {
        System.out.println("added for ya: " + task);
    }

    /**
     * Prints the task list.
     *
     * @param tasks The list of all current tasks.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list! :");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarkTaskMessage(Task task) {
        System.out.println("Yayyyy :), I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Prints a message when a task is marked as undone.
     *
     * @param task The task that was marked as undone.
     */
    public void printUnmarkTaskMessage(Task task) {
        System.out.println("OK :( , I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Prints the goodbye message when the application exits.
     */
    public void printGoodbyeMessage() {
        System.out.println("BYE BYE. Hope to see you again soon! xo xo");
    }

    /**
     * Prints a message when a command is missing a required description.
     *
     * @param command The command that was missing a description.
     */
    public void printEmptyMessage(String command) {
        System.out.println("Heyy, You need to include a message for this task: " + command);
    }

    /**
     * Prints a message when a command argument is not numerical.
     *
     * @param command The command with the invalid argument.
     */
    public void printNotANumber(String command) {
        System.out.println("NOOOOOO >:( The argument for " + command + " must be a number.");
    }

    /**
     * Prints a message when a task index is out of range.
     *
     * @param command The command with the out of range index.
     */
    public void printOutOfRange(String command) {
        System.out.println("Oopsie Daisies :/ The task index given for " + command + " is out of range.");
    }

    /**
     * Prints a message when the deadline format is incorrect.
     */
    public void printBadDeadlineFormat() {
        System.out.println("Damnn the deadline format is wrong. Use: deadline <task> /by <time>");
    }

    /**
     * Prints a message when the event format is incorrect.
     */
    public void printBadEventFormat() {
        System.out.println("OOPS!!! The event format is wrong. Use: event <task> /from <start time> /to <end time>");
    }

    /**
     * Prints a message when an unknown command is entered.
     */
    public void printUnknownCommand() {
        System.out.println("Sorry Mate, Don't know what you're on about.");
    }

    /**
     * Prints a message when a task is deleted.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task to delete.
     */
    public void printDeleteMessage(ArrayList<Task> tasks, int index) {
        System.out.println("Okay noteddd, I will delete this task:");
        System.out.println(tasks.get(index));
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param count The number of tasks.
     */
    public void printListCountMessage(int count) {
        System.out.println("Now you have " + count + " task(s) in da list :o");
    }

    /**
     * Prints the tasks found from a search.
     *
     * @param results The list of tasks found.
     */
    public void printFoundTasks(ArrayList<Task> results) {
        System.out.println("Here are the tasks you searched for in the list!");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i));
        }
    }

    /**
     * Prints a message when no tasks are found from a search.
     */
    public void printFoundNoTasks() {
        System.out.println("Sorryy, didn't manage to find any tasks you searched for:(");
    }

}
