package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Task;
import Nova.task.TaskList;
import Nova.ui.TextUi;

/**
 * Represents a command that lists all tasks in the current task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     * If the task list is empty, shows a message.
     * Otherwise, prints the number of tasks and their details.
     *
     * @param tasks   The TaskList containing all current tasks.
     * @param ui      The TextUi instance for interacting with the user.
     * @param storage The Storage instance for saving/loading tasks (not used here).
     * @throws NovaException If an error occurs during execution eg. getTask(taskIndex).
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException {
        if (tasks.isEmpty()) {
            ui.showMessage(" No tasks in this list.");
        } else {
            ui.showLineSeparator();
            System.out.println(" Number of tasks in this list: " + tasks.getTasksSize());
            System.out.println(" Here are the tasks in your list:");
            for (int taskIndex = 1; taskIndex <= tasks.getTasksSize(); taskIndex++) {
                Task task = tasks.getTask(taskIndex);
                System.out.println(" " + (taskIndex) + ". " + task);
            }
            ui.showLineSeparator();
        }
    }
}
