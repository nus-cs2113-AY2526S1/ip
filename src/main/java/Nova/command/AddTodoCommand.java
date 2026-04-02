package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Task;
import Nova.task.TaskList;
import Nova.task.Todo;
import Nova.ui.TextUi;

/**
 * Represents a command to add a Todo task to the task list.
 * The command parses user input to create a Todo with a description.
 */
public class AddTodoCommand extends Command {
    private final String arguments;

    /**
     * Constructs an AddTodoCommand with the specified user input.
     *
     * @param arguments The full arguments string provided by the user
     *                  in the format: "<description>".
     */
    public AddTodoCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command by creating a new Todo task from the arguments
     * and adding it to TaskList, displaying it by the UI, and saving the
     * updated list to Storage.
     *
     * @param tasks The TaskList containing the current tasks.
     * @param ui The TextUi instance for displaying messages to the user.
     * @param storage The Storage instance for saving/loading the updated task list.
     * @throws NovaException If the argument id empty.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException {
        if (arguments.isEmpty()) {
            throw new NovaException("Please use the format: todo <desc>");
        }
        Task newTask = new Todo(arguments);
        tasks.addTask(newTask);
        ui.showAddedTask(newTask, tasks.getTasksSize());
        storage.saveTasks(tasks);
    }
}
