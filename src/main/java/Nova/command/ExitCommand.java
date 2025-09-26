package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.TaskList;
import Nova.ui.TextUi;

/**
 * Represent a command to exit the Nova chatbot.
 * When executed, this command will display the goodbye message
 * and signal the application to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by showing a goodbye message to the user.
     *
     * @param tasks   The TaskList containing all current tasks (not used here).
     * @param ui      The TextUi instance for interacting with the user.
     * @param storage The Storage instance for saving/loading tasks (not used here).
     * @throws NovaException This command does not throw any exceptions.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException {
        ui.showByeMessage();
    }

    /**
     * Returns {@code true} to indicate that this command ends the application.
     *
     * @return {@code true}, since this command signals application termination.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
