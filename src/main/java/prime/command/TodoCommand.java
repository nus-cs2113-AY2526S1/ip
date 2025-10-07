package prime.command;

import prime.exceptions.EmptyDescriptionException;
import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.parser.CommandType;
import prime.task.ToDo;
import prime.ui.UserInterface;

/**
 * Represents a command to add a {@link ToDo} task to the task list.
 * <p>
 * The command requires a non-empty description. If the description
 * is empty, an {@link EmptyDescriptionException} is thrown.
 * </p>
 */
public class TodoCommand extends Command {

    /**
     * Constructs a new {@code TodoCommand} with the specified arguments.
     *
     * @param arguments the description of the ToDo task
     */
    public TodoCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the command by creating a new {@link ToDo} task and adding it
     * to the {@link TaskManager}.
     *
     * @param taskManager the task manager handling all tasks
     * @param ui          the user interface to display messages
     * @throws PrimeException if the description is empty or adding the task fails
     */
    @Override
    public void execute(TaskManager taskManager, UserInterface ui) throws PrimeException {
        if (arguments.isEmpty()) {
            throw new EmptyDescriptionException(CommandType.TODO);
        }
        taskManager.addTask(new ToDo(arguments), ui);
    }

}
