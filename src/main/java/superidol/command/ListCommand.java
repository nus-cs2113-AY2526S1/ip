package superidol.command;

import superidol.storage.Storage;
import superidol.tasklist.TaskList;

public class ListCommand extends Command {
    public ListCommand() {}

    public void execute(TaskList taskList, Storage storage) {
        taskList.showList();
    }
}
