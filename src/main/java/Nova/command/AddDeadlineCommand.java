package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Deadline;
import Nova.task.TaskList;
import Nova.ui.TextUi;

public class AddDeadlineCommand extends Command {
    private final String arguments;

    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

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
            throw new NovaException("Please use the format: deadline <desc> /by <time>");
        }
    }
}
