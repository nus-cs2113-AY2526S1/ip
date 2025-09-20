package superidol.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDate time;

    public Deadline(String task, String time) {
        super();
        this.task = task;
        try {
            this.time = LocalDate.parse(time);
        } catch  (DateTimeParseException e) {
            throw e;
        }
    }

    public Deadline(String task, String time, boolean isDone) {
        super();
        this.task = task;
        this.isDone = isDone;
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public String getTask() {
        return "[D]" + super.getTask() + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +")";
    }

    public String saveData() {
        String data = "D|";
        if (this.isDone) {
            data += "1|";
        } else {
            data += "0|";
        }
        data += this.task + "|";
        data += this.time.toString();
        return data;
    }

    public boolean isValid(LocalDate time) {
        if (time.isAfter(this.time)) {
            return false;
        }
        return true;
    }
}
