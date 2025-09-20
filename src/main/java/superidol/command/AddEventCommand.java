package superidol.command;

import superidol.storage.Storage;
import superidol.task.Event;
import superidol.tasklist.TaskList;

public class AddEventCommand extends Command{
    private String task;
    private String startTime;
    private String endTime;

    public AddEventCommand(String task, String startTime, String endTime) {
        this.task = task;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void execute(TaskList taskList, Storage storage) {
        Event event = new Event(task, startTime, endTime);
        taskList.addTask(event, true);
    }

}
