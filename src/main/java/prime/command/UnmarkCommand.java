package prime.command;

import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.ui.UserInterface;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface ui) throws PrimeException {
        try {
            int taskNo = Integer.parseInt(arguments);
            taskManager.unmarkTask(taskNo, ui);
        } catch (NumberFormatException e) {
            throw new PrimeException("Please enter a valid task number!");
        }
    }
}
