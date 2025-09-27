package jackson.command;

import jackson.io.Ui;
import jackson.io.Storage;
import jackson.task.TaskManager;

public class ExitCommand extends Command {
    /**
     * Execute the ExitCommand to exit the program.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @param taskManager The task manager to manage the tasks.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) {
        ui.showExitMessage();
    }

    /**
     * Indicates that this command is an exit command.
     * @return true, as this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
