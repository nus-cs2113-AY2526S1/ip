package command;

import exception.TwinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command to display all tasks in the TaskList.
 * Does not modify tasks or storage.
 */
public class ListCommand extends Command {

    /**
     * Indicates that this command does not exit the application.
     * @return false always
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the list command.
     * Shows all tasks if the task list is not empty.
     * Throws TwinException if the task list is empty.
     *
     * @param ui UI instance to interact with the user
     * @param storage Storage instance (not used for this command)
     * @param listOfUserTasks TaskList containing all tasks
     * @throws TwinException if the task list is empty
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList listOfUserTasks) throws TwinException {
        if (listOfUserTasks.size() > 0) {
            ui.showTasks(listOfUserTasks);
        } else {
            throw new TwinException("list is empty currently!");
        }
    }
}
