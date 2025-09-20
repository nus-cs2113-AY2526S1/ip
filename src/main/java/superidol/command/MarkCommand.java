package superidol.command;

import superidol.storage.Storage;
import superidol.task.Task;
import superidol.tasklist.TaskList;
import superidol.ui.Ui;

public class MarkCommand extends Command {
    private boolean isDone;
    private int taskId;

    public MarkCommand(int taskId, boolean isDone) {
        super();
        this.isDone = isDone;
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Storage storage) {
        if (taskId >= 1 && taskId <= Task.taskCount) {
            Task targetTask = taskList.getTask(taskId - 1);
            targetTask.setIsDone(isDone);
            Ui.respondMarking(targetTask, isDone);
        } else {
            Ui.respondOutOfListRange();
        }
    }
}
