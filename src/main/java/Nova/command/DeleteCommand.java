package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Task;
import Nova.task.TaskList;
import Nova.ui.TextUi;

/**
 * Represents a command to delete a task from the task list.
 * The task is identified by its task numer in the displayed list.
 */
public class DeleteCommand extends Command {
    private final String arguments;

    /**
     * Constructs a DeleteCommand with the specified argument.
     *
     * @param arguments The user input specifying which task number to delete.
     */
    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the delete command.
     * <ul>
     *     <li>Parses the task number from user input</li>
     *     <li>Deletes the corresponding task from the task list</li>
     *     <li>Updates the UI and storage with the change</li>
     * </ul>
     *
     * @param tasks The TaskList containing the current tasks.
     * @param ui The TextUi instance for displaying messages to the user.
     * @param storage The Storage instance for saving/loading the updated task list.
     * @throws NovaException If the input is invalid or task number is out of range.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException {
        try {
            int taskNumber =  Integer.parseInt(arguments);
            Task task = tasks.deleteTask(taskNumber);
            ui.showDeletedTask(task, tasks.getTasksSize());
        } catch (NumberFormatException e) {
            throw new NovaException("Invalid task number! Please enter a number between 1 and " + tasks.getTasksSize());
        }
        storage.saveTasks(tasks);
    }
}
