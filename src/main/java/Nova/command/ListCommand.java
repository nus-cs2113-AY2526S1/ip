package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Task;
import Nova.task.TaskList;
import Nova.ui.TextUi;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException {
        if (tasks.isEmpty()) {
            ui.showMessage(" No tasks in this list.");
        } else {
            ui.showLineSeparator();
            System.out.println(" Number of tasks in this list: " + tasks.getTasksSize());
            System.out.println(" Here are the tasks in your list:");
            for (int i = 1; i <= tasks.getTasksSize(); i++) {
                Task task = tasks.getTask(i);
                System.out.println(" " + (i) + ". " + task);
            }
            ui.showLineSeparator();
        }
    }
}
