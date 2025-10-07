package prime.command;

import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.ui.UserInterface;

/**
 * Represents a command to delete a task from the task list.
 * <p>
 * The command requires a task number as an argument. If the task number
 * is invalid or cannot be parsed, a {@link PrimeException} is thrown.
 * </p>
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a new {@code DeleteCommand} with the specified arguments.
     *
     * @param arguments the user-provided arguments, expected to be the task number
     */
    public DeleteCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the command by deleting the specified task from the {@link TaskManager}.
     *
     * @param taskManager the task manager containing all tasks
     * @param ui          the user interface to display messages
     * @throws PrimeException if the task number is invalid or cannot be parsed
     */
    @Override
    public void execute(TaskManager taskManager, UserInterface ui) throws PrimeException {
        try {
            int taskNo = Integer.parseInt(arguments);
            taskManager.deleteTask(taskNo, ui);
        } catch (NumberFormatException e) {
            throw new PrimeException("Please enter a valid number");
        }
    }
}
