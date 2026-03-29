package Siao.Command;

import Siao.Parser.Parser;
import Siao.management.PrintManager;
import Siao.task.Task;

import java.util.ArrayList;

import Siao.management.Storage;

/**
 * Deletes a task from the task list and updates the storage file.
 * <p>
 * The task to delete is determined by the second element of the user input array
 * (splitInput[1]). The method first parses the task index using {@link Parser#parseTaskIndex(String[])}.
 * If the input is invalid or the index is out of range, an {@link IllegalArgumentException} is thrown.
 * </p>
 *
 * @param splitInput an array of tokenized user input, where splitInput[1] should contain the task number to delete
 * @param list       the current list of tasks
 * @param storage    the Storage object used to persist changes
 * @throws IllegalArgumentException if the task index is invalid, missing, or out of range
 */
public class DeleteCommand {
    public static void deleteTask(String[] splitInput, ArrayList<Task> list, Storage storage) {
        int deleteIndex = Parser.parseTaskIndex(splitInput);

        if (deleteIndex <= -1) {
            throw new IllegalArgumentException("What task do you want to delete?");
        }

        if (deleteIndex > list.size()) {
            throw new IllegalArgumentException("Invalid task index, delete something else");
        }

        PrintManager.printDeletedTask(list, deleteIndex);
        storage.saveAllTasks(list);
    }
}
