package commands;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import exceptions.PepException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws PepException {
        tasks.markTask(index);
        Task task = tasks.getTask(index);
        ui.showMarked(task);
    }
}