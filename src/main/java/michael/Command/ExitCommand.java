package michael.Command;

import michael.Storage.Storage;
import michael.TaskList.Task;
import michael.Ui.UserMessages;

import java.util.ArrayList;

/**
 * Represents a command to exit the application.
 * Displays an exit message to the user.
 */
public class ExitCommand extends Command {
    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {
    }

    /**
     * Executes the exit command, displaying an exit message.
     *
     * @param tasks   The list of tasks
     * @param ui      The user interface for messages
     * @param storage The storage handler
     */
    @Override
    public void executeCommand(ArrayList<Task> tasks, UserMessages ui, Storage storage) {
        ui.exitMessage();
    }

}
