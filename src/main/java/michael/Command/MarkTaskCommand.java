package michael.Command;

import michael.Storage.Storage;
import michael.TaskList.Task;
import michael.Ui.UserMessages;

import java.util.ArrayList;

/**
 * Represents a command to mark a task as done.
 * Updates the task status and storage, and displays a message.
 */
public class MarkTaskCommand extends Command {

    private final int currentTaskIndex;

    /**
     * Constructs a MarkTaskCommand for the specified task index.
     *
     * @param currentTask The index of the task to mark
     */
    public MarkTaskCommand(int currentTask) {
        this.currentTaskIndex = currentTask;
    }

    /**
     * Executes the mark command, updating the task status and storage, and displaying a message.
     *
     * @param tasks   The list of tasks
     * @param ui      The user interface for messages
     * @param storage The storage handler
     */
    @Override
    public void executeCommand(ArrayList<Task> tasks, UserMessages ui, Storage storage) {
        Task currentTask = tasks.get(currentTaskIndex);
        currentTask.markAsDone();
        storage.writeToPosition(currentTaskIndex, "0", "1");
        ui.markTaskMessage(currentTask);
    }
}
