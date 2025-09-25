package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Task;
import Nova.task.TaskList;
import Nova.task.Todo;
import Nova.ui.TextUi;

public class AddTodoCommand extends Command {
    private final String arguments;

    public AddTodoCommand(String arguments) {
        this.arguments = arguments;
    }

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
