package commands;

import tasks.TaskList;
import tasks.Task;
import ui.Ui;
import exceptions.PepException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws PepException {
        Task removed = tasks.deleteTask(index);
        ui.showDeleted(removed, tasks.getCount());
    }
}