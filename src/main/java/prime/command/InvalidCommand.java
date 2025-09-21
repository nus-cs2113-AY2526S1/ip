package prime.command;

import prime.exceptions.InvalidCommandException;
import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.ui.UserInterface;

public class InvalidCommand extends Command {
    public InvalidCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface ui) throws PrimeException {
        throw new InvalidCommandException();
    }
}
