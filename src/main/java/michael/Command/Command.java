package michael.Command;

import michael.Storage.Storage;
import michael.Ui.UserMessages;
import michael.TaskList.Task;

import java.util.ArrayList;

/**
 * Abstract base class for all command types.
 * Defines the interface for executing commands on the task list.
 */
public abstract class Command {
    /**
     * Executes the command, performing its specific action on the task list.
     *
     * @param tasks   The list of tasks
     * @param ui      The user interface for messages
     * @param storage The storage handler
     */
    public abstract void executeCommand(ArrayList<Task> tasks, UserMessages ui, Storage storage);
}
