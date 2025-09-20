package superidol.command;

import superidol.storage.Storage;
import superidol.task.Deadline;
import superidol.tasklist.TaskList;

public class AddDeadlineCommand extends Command{
    private String task;
    private String time;

    public AddDeadlineCommand(String task, String time){
        super();
        this.task = task;
        this.time = time;
    }

    public void execute(TaskList taskList, Storage storage) {
        Deadline deadline = new Deadline(task, time);
        taskList.addTask(deadline, true);
    }
}
