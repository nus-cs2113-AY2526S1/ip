package prime.command;

import prime.manager.TaskManager;
import prime.ui.UserInterface;

/**
 * Represents a command to exit the Prime application.
 * <p>
 * When executed, it prints a goodbye message to the user and signals
 * the application to terminate.
 * </p>
 */
public class ByeCommand extends Command {

    /**
     * Constructs a new {@code ByeCommand} with the specified arguments.
     *
     * @param arguments the user-provided arguments (ignored for this command)
     */
    public ByeCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the command by displaying the goodbye message via {@link UserInterface}.
     *
     * @param taskManager the task manager (not used)
     * @param ui          the user interface to display the goodbye message
     */
    @Override
    public void execute(TaskManager taskManager, UserInterface ui) {
        ui.printByeMessage();
    }

    /**
     * Indicates that this command will exit the application.
     *
     * @return {@code true} always, signaling the application to terminate
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
