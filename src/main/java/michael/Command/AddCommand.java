package michael.Command;

import static michael.Michael.numberTasks;

import michael.Ui.UserMessages;
import michael.Storage.Storage;
import michael.TaskList.Task;

import java.util.ArrayList;

/**
 * Represents a command to add a new task to the task list.
 * Handles both new tasks and tasks loaded from storage.
 */
public class AddCommand extends Command {
    private final Task task;
    private final boolean isDataNew;

    /**
     * Constructs an AddCommand with the specified task and data source flag.
     *
     * @param task      The task to add
     * @param isDataNew True if the task is new, false if loaded from file
     */
    public AddCommand(Task task, boolean isDataNew) {
        this.task = task;
        this.isDataNew = isDataNew;
    }

    /**
     * Executes the add command, adding the task to the list and displaying a message if new.
     *
     * @param tasks   The list of tasks
     * @param ui      The user interface for messages
     * @param storage The storage handler
     */
    @Override
    public void executeCommand(ArrayList<Task> tasks, UserMessages ui, Storage storage) {
        tasks.add(task);
        numberTasks++;
        if (isDataNew) {
            ui.addTaskMessage(task, tasks.size());
        }
    }
}
