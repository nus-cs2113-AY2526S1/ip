package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.Event;
import Nova.task.TaskList;
import Nova.ui.TextUi;

public class AddEventCommand extends Command {
    private final String arguments;

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

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
            throw new NovaException("Please use the format: event <desc> /from <start> /to <end>");
        }
    }
}
