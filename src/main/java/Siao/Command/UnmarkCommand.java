package Siao.Command;

import Siao.Parser.Parser;
import Siao.management.Constants;
import Siao.management.PrintManager;
import Siao.management.Storage;
import Siao.task.Task;

import java.util.ArrayList;

/**
 * Marks a task in the list as not done (unmarks it) and updates the storage file.
 * <p>
 * The task to unmark is determined by the second element of the user input array
 * (splitInput[1]). The method first parses the task index using {@link Parser#parseTaskIndex(String[])}.
 * If the input is invalid or the index is out of range, an {@link IllegalArgumentException} is thrown.
 * </p>
 *
 * @param splitInput the tokenized user input array, where splitInput[1] should contain the task number
 * @param list       the current list of tasks
 * @param storage    the Storage object used to persist changes
 * @throws IllegalArgumentException if the task index is invalid, the list is empty,
 *                                  or the task number is out of range
 */
public class UnmarkCommand {
    public static void unmarkTask (String[] splitInput, ArrayList<Task> list, Storage storage) {
        int unmarkIndex = Parser.parseTaskIndex(splitInput);

        if (unmarkIndex <= -1) {
            throw new IllegalArgumentException(Constants.ERROR_INDEX);
        }

        if (list.isEmpty()) {
            throw new IllegalArgumentException("No item in the list, HOW TO UNMARK?");
        } else if (unmarkIndex > list.size()) {
            throw new IllegalArgumentException(Constants.UNMARK_WRONGLY);
        }

        list.get(unmarkIndex).markUndone();
        PrintManager.printMarkUndone(list.get(unmarkIndex));
        storage.saveAllTasks(list);
    }
}
