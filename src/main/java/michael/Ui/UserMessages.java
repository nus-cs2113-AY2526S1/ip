package michael.Ui;

import michael.TaskList.Task;

import java.util.ArrayList;

import static michael.Michael.numberTasks;

/**
 *
 * This class handles user-facing messages for the chatbot.
 * Each method outputs formatted messages to the console to inform the user
 * about the application's state and operations performed on tasks.
 *
 */

public class UserMessages {

    public UserMessages() {
    }

    /**
     * Prints welcome message
     */
    public void welcomeMessage() {
        border();
        String message = "Welcome to Michael's Task Manager!\n" +
                "Let's get things done...";
        System.out.println(message);
        border();
    }

    public void border() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints exit message
     */
    public void exitMessage() {
        border();
        String message = "Okay team, that’s a wrap. I am leaving. Goodbye.";
        System.out.println(message);
        border();
    }

    /**
     * Prints all the tasks in the file
     */
    public void addTaskMessage(Task t, int numberTasks) {
        border();
        System.out.println("Boom! Instant classic task, added:");
        System.out.println(t);
        System.out.println("Now you have " + numberTasks + " task(s) in the list.");
        border();
    }

    /**
     * Prints message when each task is marked
     */
    public void markTaskMessage(Task currentTask) {
        border();
        System.out.println("Yes! Task is done. I am very proud of you. Like, manager-of-the-year proud:"
        );
        System.out.println(currentTask);
        border();
    }

    /**
     * Prints message when each task is unmarked
     */
    public void unmarkTaskMessage(Task currentTask) {

        border();
        System.out.println("I’m not superstitious, but I am a little ’stitious… Let’s unmark that task:");
        System.out.println(currentTask);
        border();
    }

    /**
     * Prints message when each task is deleted
     */
    public void deleteTaskMessage(Task removedTask, int numberTasks) {
        border();
        System.out.println("Goodbye, task. The worst thing about tasks was the dementors. Just kidding. Deleted:");
        System.out.println(removedTask);
        System.out.println("Now you have " + numberTasks + " task(s) in the list.");
        border();
    }

    /**
     * Prints message when users asks to see the list of tasks
     */
    public void showListMessage(ArrayList<Task> tasks, int numberTasks) {
        border();
        System.out.println("Hello there! Certainly, here are the tasks in your list:");
        for (int i = 0; i < numberTasks; i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("You currently have " + numberTasks + " task(s)");
        border();
    }

    /**
     * Prints message when users asks to find all tasks that contains a specific keyword
     */
    public void findTasksMessage() {
        border();
        System.out.println("Here are the matching tasks in your list:");
    }

}
