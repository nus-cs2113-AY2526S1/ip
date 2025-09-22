package prime.command;

import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.ui.UserInterface;

/**
 * Represents a command to mark a task as completed (done) in the task list.
 * <p>
 * The command requires a task number as an argument. If the task number
 * is invalid or cannot be parsed, a {@link PrimeException} is thrown.
 * </p>
 */
public class MarkCommand extends Command {

    /**
     * Constructs a new {@code MarkCommand} with the specified arguments.
     *
     * @param arguments the user-provided arguments, expected to be the task number
     */
    public MarkCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the command by marking the specified task as done in the {@link TaskManager}.
     *
     * @param taskManager the task manager handling all tasks
     * @param ui          the user interface to display messages
     * @throws PrimeException if the task number is invalid or cannot be parsed
     */
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