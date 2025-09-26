package Nova.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task which has a description,
 * a start date/time, and an end date/time.
 */
public class Event extends Task{
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start date/time of the event in "d/M/yyyy HHmm" format
     * @param to the end date/time of the event in "d/M/yyyy HHmm" format
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns a string representation of the Event task for display.
     * <p>
     * Example: [E][ ] hackathon (from: Oct 5 2025 1000 to: Oct 6 2025 1800)
     *
     * @return the string representation of the Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + ")";
    }

    /**
     * Returns the start date/time of the event in "dd/MM/yyyy HHmm" format.
     *
     * @return the formatted start date/time
     */
    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Returns the end date/time of the event in "dd/MM/yyyy HHmm" format.
     *
     * @return the formatted end date/time
     */
    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
