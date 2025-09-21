package prime.command;

import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.task.Task;
import prime.ui.UserInterface;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String arguments) {
        super(arguments);
    }

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
