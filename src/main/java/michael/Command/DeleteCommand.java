package michael.Command;

import michael.Storage.Storage;
import michael.TaskList.Task;
import michael.Ui.UserMessages;

import java.util.ArrayList;

import static michael.Michael.numberTasks;

/**
 * Represents a command to delete a task from the task list.
 * Handles removal of the task and updates storage and UI.
 */
public class DeleteCommand extends Command {

    private final int currentTaskIndex;

    /**
     * Constructs a DeleteCommand for the specified task index.
     *
     * @param currentTask The index of the task to delete
     */
    public DeleteCommand(int currentTask) {
        this.currentTaskIndex = currentTask;
    }

    /**
     * Executes the delete command, removing the task and updating storage and to print message to users.
     *
     * @param tasks   The list of tasks
     * @param ui      The user interface for messages
     * @param storage The storage handler
     */
    @Override
    public void executeCommand(ArrayList<Task> tasks, UserMessages ui, Storage storage) {
        Task removedTask = tasks.get(currentTaskIndex);
        tasks.remove(removedTask);
        numberTasks--;
        storage.deleteTask(currentTaskIndex);
        ui.deleteTaskMessage(removedTask, numberTasks);
    }
}
