package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Task;
import Nova.task.TaskList;
import Nova.ui.TextUi;

public class MarkCommand extends Command {
    private final int taskNumber;
    private final boolean done;

    public MarkCommand(String arguments, boolean done) throws NovaException {
        try {
            this.taskNumber = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new NovaException("Invalid task number!");
        }
        this.done = done;
    }

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
