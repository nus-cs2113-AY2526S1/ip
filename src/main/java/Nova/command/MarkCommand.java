package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Task;
import Nova.task.TaskList;
import Nova.ui.TextUi;

/**
 * Represents a command to mark or unmark a task in the task list.
 */
public class MarkCommand extends Command {
    private final int taskNumber;
    private final boolean done;

    /**
     * Creates a MarkCommand.
     *
     * @param arguments The user input specifying the task number.
     * @param done      Whether to mark (true) or unmark (false) the task.
     * @throws NovaException If the argument is not a valid integer.
     */
    public MarkCommand(String arguments, boolean done) throws NovaException {
        try {
            this.taskNumber = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new NovaException("Invalid task number! Please try again.");
        }
        this.done = done;
    }

    /**
     * Executes the command to mark or unmark a task.
     *
     * @param tasks   The TaskList containing all current tasks.
     * @param ui      The TextUi instance for interacting with the user.
     * @param storage The Storage instance for saving the updated tasks.
     * @throws NovaException If the task number is out of range.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException {
            Task task = tasks.getTask(taskNumber);

            if (done) {
                task.markAsDone();
                ui.showMarkedTask(task);
            } else {
                task.markAsNotDone();
                ui.showUnmarkedTask(task);
            }
            storage.saveTasks(tasks);
    }
}
