package superidol.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * constructor need to check for valid time string and timestamp
     *
     * @param task
     * @param startTime
     * @param endTime
     * @throws DateTimeException
     */
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

    /**
     * constructor need to check for valid time string and timestamp
     * set isDone to load data from file
     *
     * @param task
     * @param startTime
     * @param endTime
     * @param isDone
     * @throws DateTimeException
     */
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

    /**
     *
     * @param time
     * @return boolean if the event is still valid
     */
    public boolean isValid(LocalDate time) {
        if (time.isAfter(this.endTime)) {
            return false;
        }
        return true;
    }
}
