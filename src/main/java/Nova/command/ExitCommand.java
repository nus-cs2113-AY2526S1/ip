package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.TaskList;
import Nova.ui.TextUi;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException {
        ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
