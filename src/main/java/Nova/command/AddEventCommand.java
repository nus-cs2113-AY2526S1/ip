package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Event;
import Nova.task.TaskList;
import Nova.ui.TextUi;

/**
 * Represents a command to add an Event task to the task list
 * The command parse user input to create an Event with a description, start time,
 * and end time.
 */
public class AddEventCommand extends Command {
    private final String arguments;

    /**
     * Constructs an AddEventCommand with the specified user input.
     *
     * @param arguments The full argument provided bt the user in the format:
     *                  "<description> /from <date-time> /to <date-time>"
     */
    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command by creating a new Event task from the arguments,
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
            String[] userInputArray = arguments.split("/from|/to");
            String description = userInputArray[0].trim();
            String from = userInputArray[1].trim();
            String to = userInputArray[2].trim();
            Event newTask = new Event(description, from, to);
            tasks.addTask(newTask);
            ui.showAddedTask(newTask, tasks.getTasksSize());
            storage.saveTasks(tasks);
        } catch (Exception e) {
            throw new NovaException("Please use the format: "
                    + "event <desc> /from <DD/MM/YYYY HHmm> /to <DD/MM/YYYY HHmm>");
        }
    }
}
