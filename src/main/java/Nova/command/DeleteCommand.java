package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Task;
import Nova.task.TaskList;
import Nova.ui.TextUi;

public class DeleteCommand extends Command {
    private final String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

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
