package michael.Command;

import michael.Storage.Storage;
import michael.TaskList.Task;
import michael.Ui.UserMessages;

import java.util.ArrayList;

import static michael.Michael.numberTasks;

/**
 * Represents a command to display the current list of tasks.
 * Shows all tasks and the total count to the user.
 */
public class ShowListCommand extends Command {
    /**
     * Constructs a ShowListCommand.
     */
    public ShowListCommand() {
    }

    /**
     * Executes the show list command, which contains method to printing all tasks and the count.
     *
     * @param tasks   The list of tasks
     * @param ui      The user interface for messages
     * @param storage The storage handler
     */
    @Override
    public void executeCommand(ArrayList<Task> tasks, UserMessages ui, Storage storage) {
        ui.showListMessage(tasks, numberTasks);
    }
}
