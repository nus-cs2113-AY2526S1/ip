package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.TaskList;
import Nova.ui.TextUi;

public abstract class Command {
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException;

    public boolean isExit() {
        return false;
    }
}
