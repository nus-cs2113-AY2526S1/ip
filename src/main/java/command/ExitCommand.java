package command;
import exception.TwinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents the command to exit the program.
 * <p>
 * When executed, this command displays a goodbye message,
 * saves the current list of tasks to storage, and signals the
 * application to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Indicates that this command will cause the program to exit.
     *
     * @return true since this is the exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the exit command by showing a goodbye message
     * and saving all current tasks to persistent storage.
     *
     * @param ui the user interface handler
     * @param storage the storage system for saving tasks
     * @param listOfUserTasks the list of tasks currently managed
     * @throws TwinException if an error occurs while saving tasks
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList listOfUserTasks) throws TwinException {
        ui.showGoodbye();
        // could also trigger saving before exit
        storage.writeToFile(listOfUserTasks);
    }
}
