package commands;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import exceptions.PepException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws PepException {
        tasks.unmarkTask(index);
        Task task = tasks.getTask(index);
        ui.showUnmarked(task);
    }
}