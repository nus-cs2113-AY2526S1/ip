package superidol.command;

import superidol.storage.Storage;
import superidol.tasklist.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PrintByTimeCommand extends Command{
    private LocalDate time;

    /**
     * Constructor need to check if the time string is valid
     *
     * @param time
     */
    public PrintByTimeCommand(String time){
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public void execute(TaskList taskList, Storage storage){
        taskList.printByTime(time);
    }
}
