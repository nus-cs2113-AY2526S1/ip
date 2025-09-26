package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Deadline;
import Nova.task.TaskList;
import Nova.ui.TextUi;

/**
 * Represents a command to add a Deadline task to the task list.
 * The command parses user input to create a Deadline with a description
 * and a due date/time.
 */
public class AddDeadlineCommand extends Command {
    private final String arguments;

    /**
     * Constructs an AddDeadlineCommand with the specified user input.
     *
     * @param arguments The full arguments string provided by the user
     *                  in the format: "<description> /by <date-time>".
     */
    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command by creating a new Deadline task from the arguments
     * and adding it to TaskList, displaying it by the UI, and saving the
     * updated list to Storage.
     *
     * @param tasks The TaskList containing the current tasks.
     * @param ui The TextUi instance for displaying messages to the user.
     * @param storage The Storage instance for saving/loading the updated task list.
     * @throws NovaException If the arguments are invalid or in the wrong format.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException {
        try {
            String[] userInputArray = arguments.split("/by", 2);
            String description = userInputArray[0].trim();
            String by = userInputArray[1].trim();
            Deadline newTask = new Deadline(description, by);
            tasks.addTask(newTask);
            ui.showAddedTask(newTask, tasks.getTasksSize());
            storage.saveTasks(tasks);
        } catch (Exception e) {
            throw new NovaException("Please use the format: deadline <desc> /by <DD/MM/YYYY HHmm>");
        }
    }
}
