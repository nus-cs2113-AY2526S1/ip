package Siao.Command;

import Siao.management.Constants;
import Siao.management.PrintManager;
import Siao.management.Storage;
import Siao.task.Deadline;
import Siao.task.Task;

import java.util.ArrayList;

/**
 * Provides functionality to create and add new Deadline tasks to the task list.
 * <p>
 * The class validates that the user input contains the required "/by" keyword
 * before creating the {@link Deadline} object. Once validated, the new deadline is added to the list,
 * a confirmation is printed, and the task is saved to storage.
 */
public class AddDeadline {

    /**
     * Creates a new Deadline task from user input, adds it to the task list, prints a confirmation,
     * and saves it to storage.
     *
     * @param list    the current list of tasks to which the new Deadline will be added
     * @param line    the user input string containing the Deadline description along with the "/by" keyword
     * @param storage the Storage object used to save the new task
     * @throws IllegalArgumentException if the input does not contain the "/by" keyword
     */
    public static void newDeadline(ArrayList<Task> list, String line, Storage storage){
        if (!isDeadlineKeyed(line)) {
            throw new IllegalArgumentException(Constants.ILLEGAL_DEADLINE_MESSAGE);
        }

        Deadline newDeadline = new Deadline(line);
        list.add(newDeadline);
        newDeadline.increaseCounter();
        PrintManager.printAddedTask(newDeadline, list);
        storage.saveTask(newDeadline);
    }

    private static boolean isDeadlineKeyed(String description){
        return description.contains("/by");
    }
}
