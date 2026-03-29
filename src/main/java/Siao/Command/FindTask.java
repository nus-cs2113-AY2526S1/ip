package Siao.Command;

import Siao.management.Constants;
import Siao.task.Task;

import java.util.ArrayList;

/**
 * Provides functionality to search for tasks in the task list based on a keyword.
 * <p>
 * The class scans the list of tasks and prints all tasks whose descriptions contain
 * the specified keyword. If no matching tasks are found or the input is invalid,
 * an {@link IllegalArgumentException} is thrown.
 */
public class FindTask {

    /**
     * Searches for tasks containing the specified keyword and prints the matching tasks.
     * <p>
     * The method expects the user input line to contain a keyword at the second position
     * (after the "find" command). All matching tasks are displayed with their index numbers.
     * </p>
     * @param line the full user input line, where the keyword is expected after the command
     * @param list the current list of tasks to search
     * @throws IllegalArgumentException if the input does not contain a keyword or no tasks match the keyword
     */
    public static void returnTask (String line, ArrayList<Task> list) {
        String[] parts = line.split(" ");
        if (parts.length < 2) {
            throw new IllegalArgumentException(Constants.WRONG_FIND);
        }

        String keyword = parts[1];

        boolean found = false;

        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                found = true;
                break;
            }
        }

        if (found) {
            int indexNumber = 1;
            System.out.printf(Constants.DIVIDER + "\n" + Constants.FOUND_ITEM + "\n");
            for (Task task : list) {
                if (task.getDescription().contains(keyword)) {
                    System.out.printf("%d. %s", indexNumber, task.toString());
                    indexNumber++;
                }
            }
            System.out.printf(Constants.DIVIDER + "\n");
        } else {
            throw new IllegalArgumentException(Constants.WRONG_FIND);
        }

    }
}
