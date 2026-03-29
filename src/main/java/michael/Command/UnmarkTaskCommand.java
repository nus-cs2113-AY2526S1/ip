package michael.Command;

import michael.Storage.Storage;
import michael.TaskList.Task;
import michael.Ui.UserMessages;

import java.util.ArrayList;

/**
 * Represents a command to unmark a task as done (mark as not completed).
 * Updates the task status and storage, and displays a message.
 */
public class UnmarkTaskCommand extends Command {
    private final int currentTaskIndex;

    /**
     * Constructs an UnmarkTaskCommand for the specified task index.
     *
     * @param currentTask The index of the task to unmark
     */
    public UnmarkTaskCommand(int currentTask) {
        this.currentTaskIndex = currentTask;
    }

    /**
     * Executes the unmark command, updating the task status and storage, and displaying a message.
     *
     * @param tasks   The list of tasks
     * @param ui      The user interface for messages
     * @param storage The storage handler
     */
    @Override
    public void executeCommand(ArrayList<Task> tasks, UserMessages ui, Storage storage) {
        Task currentTask = tasks.get(currentTaskIndex);
        currentTask.markAsUndone();
        storage.writeToPosition(currentTaskIndex, "0", "1");
        ui.unmarkTaskMessage(currentTask);
    }
}
