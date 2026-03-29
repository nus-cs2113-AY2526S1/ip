package michael.Command;

import michael.Storage.Storage;
import michael.TaskList.Task;
import michael.Ui.UserMessages;

import java.util.ArrayList;

/**
 * Represents a command to find tasks containing a specific query string.
 * Displays matching tasks to the user.
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Constructs a FindCommand with the specified query.
     *
     * @param query The string to search for in task descriptions
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the find command, displaying tasks that match the query.
     *
     * @param tasks   The list of tasks
     * @param ui      The user interface for messages
     * @param storage The storage handler
     */
    @Override
    public void executeCommand(ArrayList<Task> tasks, UserMessages ui, Storage storage) {
        ui.findTasksMessage();
        int index;
        for (Task task : tasks) {
            if (task.getDescription().contains(query)) {
                index = task.getTaskIndex();
                System.out.println(index + ". " + task);
            }
        }
        ui.border();
    }

}
