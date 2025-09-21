package prime.command;

import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.ui.UserInterface;

public class MarkCommand extends Command {

    public MarkCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface ui) throws PrimeException {
        try {
            int taskNo = Integer.parseInt(arguments);
            taskManager.markTask(taskNo, ui);
        } catch (NumberFormatException e) {
            throw new PrimeException("Please enter a valid task number!");
        }
    }
}