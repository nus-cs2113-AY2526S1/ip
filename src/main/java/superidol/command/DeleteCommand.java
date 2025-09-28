package superidol.command;

import superidol.storage.Storage;
import superidol.tasklist.TaskList;

public class DeleteCommand extends Command{
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Storage storage) {
        taskList.deleteTask(taskId);
    }
}
