package superidol.command;

import superidol.storage.Storage;
import superidol.tasklist.TaskList;

public abstract class Command {
    protected boolean isExit = false;

    public boolean getIsExit() {
        return isExit;
    }

    public abstract void execute(TaskList taskList, Storage storage);
}
