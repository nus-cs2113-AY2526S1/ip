package prime.command;

import prime.manager.TaskManager;
import prime.ui.UserInterface;

/**
 * Represents a command to list all tasks in the task list.
 * <p>
 * This command does not require any arguments. When executed,
 * it displays all current tasks via the {@link UserInterface}.
 * </p>
 */
public class ListCommand extends Command {

    /**
     * Constructs a new {@code ListCommand}.
     *
     * @param arguments the user-provided arguments (ignored for this command)
     */
    public ListCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the command by listing all tasks in the {@link TaskManager}.
     *
     * @param taskManager the task manager containing all tasks
     * @param ui          the user interface to display the tasks
     */
    @Override
    public void execute(TaskManager taskManager, UserInterface ui) {
        taskManager.listTasks(ui);
    }
}
