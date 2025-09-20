package superidol.command;

import superidol.storage.Storage;
import superidol.tasklist.TaskList;
import superidol.ui.Ui;

public class ExitCommand extends Command{
    public ExitCommand() {
        super();
        this.isExit = true;
    }

    public void execute(TaskList taskList, Storage storage) {
        Ui.goodbye();
        storage.saveToFile();
    }
}
