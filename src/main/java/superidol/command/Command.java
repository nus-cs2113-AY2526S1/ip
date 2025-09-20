package superidol.command;

import superidol.storage.Storage;
import superidol.tasklist.TaskList;

public abstract class Command {
    /**
     * Check if the program should exit after the command
     */
    protected boolean isExit = false;

    public boolean getIsExit() {
        return isExit;
    }

    /**
     * Method to execute the command
     *
     * @param taskList
     * @param storage
     */
    public abstract void execute(TaskList taskList, Storage storage);
}
