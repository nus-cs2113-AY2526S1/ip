package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs during a specific time period.
 * An {@code Event} stores a description, a start date/time, and an end date/time.
 * <p>
 * Input times are expected in the format {@code yyyy-MM-dd HHmm}
 * (e.g., {@code 2019-12-02 1800}). When displayed, the times are formatted
 * in a more user-friendly style such as {@code Dec 02 2019 18:00}.
 * </p>
 */
public class Event extends Task {
    /** The start date and time of the event. */
    protected LocalDateTime from;

    /** The end date and time of the event. */
    protected LocalDateTime to;

    /**
     * Constructs an {@code Event} with the specified description, start, and end times.
     *
     * @param description the description of the event
     * @param from the start date/time in {@code yyyy-MM-dd HHmm} format
     * @param to the end date/time in {@code yyyy-MM-dd HHmm} format
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the start date/time of this event.
     *
     * @return the start date/time as a {@link LocalDateTime}
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date/time of this event.
     *
     * @return the end date/time as a {@link LocalDateTime}
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns the string representation of this event task,
     * including its description, completion status, and formatted start/end times.
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString()
                + " (from: " + from.format(fmt)
                + " to: " + to.format(fmt) + ")";
    }
}