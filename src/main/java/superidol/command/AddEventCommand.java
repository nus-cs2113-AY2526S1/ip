package superidol.command;

import superidol.storage.Storage;
import superidol.task.Event;
import superidol.tasklist.TaskList;
import superidol.ui.Ui;

import java.time.DateTimeException;

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
        try {
            Event event = new Event(task, startTime, endTime);
            taskList.addTask(event, true);
        } catch (DateTimeException e) {
            Ui.respondInvalidEvent();
        }
    }

}
