package Siao.Command;

import Siao.management.Constants;
import Siao.management.PrintManager;
import Siao.management.Storage;
import Siao.task.Event;
import Siao.task.Task;

import java.util.ArrayList;

/**
 * Provides functionality to create and add new Event tasks to the task list.
 * <p>
 * The class validates that the user input contains the required "/from" and "/to" keywords
 * before creating the {@link Event} object. Once validated, the new event is added to the list,
 * a confirmation is printed, and the task is saved to storage.
 */
public class AddEvent {

    /**
     * Creates a new Event task from user input, adds it to the task list, prints a confirmation,
     * and saves it to storage.
     *
     * @param list    the current list of tasks to which the new Event will be added
     * @param line    the user input string containing the Event description along with "/from" and "/to" times
     * @param storage the Storage object used to save the new task
     * @throws IllegalArgumentException if the input does not contain "/from" or "/to"
     */
    public static void newEvent(ArrayList<Task> list, String line, Storage storage){
        if (!isFromKeyed(line)) {
            throw new IllegalArgumentException(Constants.ILLEGAL_START_MESSAGE);
        } else if (!isToKeyed(line)) {
            throw new IllegalArgumentException(Constants.ILLEGAL_END_MESSAGE);
        }

        Event newEvent = new Event(line);
        list.add(newEvent);
        newEvent.increaseCounter();
        PrintManager.printAddedTask(newEvent, list);
        storage.saveTask(newEvent);
    }

    public static boolean isFromKeyed(String line){
        return line.contains("/from");
    }

    public static boolean isToKeyed(String line){
        return line.contains("/to");
    }


}
