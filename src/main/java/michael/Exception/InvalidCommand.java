package michael.Exception;

import michael.Storage.Storage;
import michael.TaskList.Task;
import michael.Ui.UserMessages;
import michael.Command.Command;

import java.util.ArrayList;

/**
 * Represents a command for invalid user instructions.
 * Displays an error message to the user when executed.
 */
public class InvalidCommand extends Command {

    private final String errorMsg;

    /**
     * Constructs an InvalidCommand with the specified error message.
     *
     * @param errorMsg The error message to display
     */
    public InvalidCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Executes the invalid command, displaying the error message.
     *
     * @param tasks   The list of tasks
     * @param ui      The user interface for messages
     * @param storage The storage handler
     */
    @Override
    public void executeCommand(ArrayList<Task> tasks, UserMessages ui, Storage storage) {
        System.out.println(errorMsg);
    }
}
