package Siao.Command;

import Siao.Parser.Parser;
import Siao.management.Constants;
import Siao.management.PrintManager;
import Siao.task.Task;

import java.util.ArrayList;
import Siao.management.Storage;

/**
 * Marks a task in the task list as done and updates the storage file.
 * <p>
 * The task to mark is determined by the second element of the user input array
 * (splitInput[1]). The method first parses the task index using {@link Parser#parseTaskIndex(String[])}.
 * If the input is invalid, the list is empty, or the index is out of range, an IllegalArgumentException is thrown.
 * </p>
 *
 * @param splitInput an array of tokenized user input, where splitInput[1] should contain the task number to mark
 * @param list       the current list of tasks
 * @param storage    the Storage object used to persist changes
 * @throws IllegalArgumentException if the task index is invalid, the list is empty, or the task number is out of range
 */
public class MarkCommand {
    public static void markTask (String[] splitInput, ArrayList<Task> list, Storage storage) {
        int markIndex = Parser.parseTaskIndex(splitInput);

        if (markIndex <= -1) {
            throw new IllegalArgumentException(Constants.ERROR_INDEX);
        }

        if (list.isEmpty()) {
            throw new IllegalArgumentException("No item in the list, HOW TO MARK?");
        } else if (markIndex> list.size()) {
            throw new IllegalArgumentException(Constants.MARK_WRONGLY);
        }
        list.get(markIndex).markDone();
        PrintManager.printMarkDone(list.get(markIndex));
        storage.saveAllTasks(list);
    }
}
