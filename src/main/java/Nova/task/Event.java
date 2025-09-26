package Nova.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + ")";
    }

    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
