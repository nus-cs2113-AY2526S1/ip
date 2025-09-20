package superidol.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task{
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String task, String startTime, String endTime) {
        super();
        this.task = task;
        try {
            this.startTime = LocalDate.parse(startTime);
            this.endTime = LocalDate.parse(endTime);
            if (this.endTime.isBefore(this.startTime)) {
                throw new DateTimeException("End time cannot be before start time");
            }
        } catch (DateTimeException e) {
            throw e;
        }
    }

    public Event(String task, String startTime, String endTime, boolean isDone) {
        super();
        this.task = task;
        this.isDone = isDone;
        try {
            this.startTime = LocalDate.parse(startTime);
            this.endTime = LocalDate.parse(endTime);
            if (this.endTime.isBefore(this.startTime)) {
                throw new DateTimeException("End time cannot be before start time");
            }
        } catch (DateTimeException e) {
            throw e;
        }
    }

    public String getTask() {
        return "[E]" + super.getTask() +
                " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public String saveData() {
        String data = "E|";
        if (this.isDone) {
            data += "1|";
        } else {
            data += "0|";
        }
        data += task + "|";
        data += startTime + "|";
        data += endTime;
        return data;
    }

    public boolean isValid(LocalDate time) {
        if (time.isAfter(this.endTime)) {
            return false;
        }
        return true;
    }
}
