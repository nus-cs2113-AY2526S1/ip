package prime.command;

import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.task.Task;
import prime.ui.UserInterface;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that match a given keyword.
 * <p>
 * Searches through all tasks in the {@link TaskManager} and displays
 * the matching tasks via the {@link UserInterface}.
 * </p>
 */
public class FindCommand extends Command {

    /**
     * Constructs a new {@code FindCommand} with the specified search keyword.
     *
     * @param arguments the search keyword to find in task descriptions
     */
    public FindCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the find command by searching for tasks containing the keyword
     * and printing the results.
     *
     * @param taskManager the task manager containing all tasks
     * @param ui          the user interface to display matching tasks
     * @throws PrimeException if no search term is provided
     */
    @Override
    public void execute(TaskManager taskManager, UserInterface ui) throws PrimeException {
        if (arguments.isEmpty()) {
            throw new PrimeException("Please provide a search term!");
        }

        ArrayList<Task> matchingTasks = taskManager.findTasks(arguments);
        if (matchingTasks.isEmpty()) {
            ui.printIndented("No tasks found!");
        } else {
            ui.printIndented("Found " + matchingTasks.size() + " tasks!");
            ui.printIndented("Here are the matching tasks from the list: ");
            for (Task task : matchingTasks) {
                ui.printIndented(task.toString());
            }
        }
    }
}
