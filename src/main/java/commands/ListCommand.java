package commands;

import exceptions.PepException;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws PepException {
        ui.showTaskList(tasks);
    }
}