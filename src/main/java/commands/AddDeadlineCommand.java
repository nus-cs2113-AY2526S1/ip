package commands;

import tasks.TaskList;
import tasks.Deadline;
import ui.Ui;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Deadline task = new Deadline(description, by);
        tasks.addTask(task);
        ui.showAdded(task.toString(), tasks.getCount());
    }
}