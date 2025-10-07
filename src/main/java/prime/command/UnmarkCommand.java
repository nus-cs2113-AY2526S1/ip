package prime.command;

import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.ui.UserInterface;

/**
 * Represents a command to mark a task as not done (unmark).
 * <p>
 * This command requires the user to provide a task number as an argument.
 * </p>
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs a new {@code UnmarkCommand} with the specified arguments.
     *
     * @param arguments the user-provided arguments, expected to be the task number
     */
    public UnmarkCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the unmark operation on the given {@link TaskManager}.
     * <p>
     * Marks the specified task as not done and displays feedback through
     * the provided {@link UserInterface}.
     * </p>
     *
     * @param taskManager the task manager handling all tasks
     * @param ui          the user interface to display messages
     * @throws PrimeException if the task number is invalid or cannot be parsed
     */
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
