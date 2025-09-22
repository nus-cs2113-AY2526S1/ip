package prime.command;

import prime.exceptions.InvalidCommandException;
import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.ui.UserInterface;

/**
 * Represents a command that is invalid or unrecognized by the Prime system.
 * <p>
 * When executed, this command always throws an {@link InvalidCommandException}.
 * </p>
 */
public class InvalidCommand extends Command {

    /**
     * Constructs a new {@code InvalidCommand} with the specified arguments.
     *
     * @param arguments the user-provided arguments (ignored for this command)
     */
    public InvalidCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the command by throwing an {@link InvalidCommandException}.
     *
     * @param taskManager the task manager (not used)
     * @param ui          the user interface (not used)
     * @throws PrimeException always thrown to indicate the command is invalid
     */
    @Override
    public void execute(TaskManager taskManager, UserInterface ui) throws PrimeException {
        throw new InvalidCommandException();
    }
}
