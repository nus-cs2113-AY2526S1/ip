package command;
import exception.TwinException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents the command to delete a task from the task list.
 * <p>
 * When executed, this command removes the specified task
 * from the user's task list and updates the UI.
 */
public class DeleteCommand extends Command {
    private int itemNumber;

    /**
     * Creates a DeleteCommand for the given task number.
     *
     * @param itemNumber the task number to delete (1-based index)
     */
    public DeleteCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * Indicates that this command does not exit the program.
     *
     * @return false since this command only deletes a task
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the delete command by removing the specified task
     * from the list and displaying confirmation to the user.
     *
     * @param ui the user interface handler
     * @param storage the storage system (not used here but kept for consistency)
     * @param listOfUserTasks the list of tasks currently managed
     * @throws TwinException if the task number is invalid (out of bounds)
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList listOfUserTasks) throws TwinException {
        if (itemNumber < 1 || itemNumber > listOfUserTasks.size()) {
            throw new TwinException("Task number " + itemNumber + " does not exist.");
        }
        Task removedTask = listOfUserTasks.remove(itemNumber - 1);
        ui.showDeletedTask(removedTask, listOfUserTasks.size());
    }
}
