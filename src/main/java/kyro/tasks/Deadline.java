package kyro.tasks;

import kyro.exceptions.KyroException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * <p>
 * A {@code Deadline} has a description and a due date/time by which
 * it must be completed.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a new {@code Deadline} task.
     *
     * @param description The description of the task.
     * @param by          The deadline in {@code yyyy-MM-dd HHmm} format.
     */
    public Deadline(String description, String by) throws KyroException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new KyroException("Date format must be: <yyyy-MM-dd HHmm>");
        }
    }

    /**
     * Returns the deadline of this task.
     *
     * @return The {@link LocalDateTime} representing the deadline.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of this deadline task for display.
     *
     * @return A formatted string with type, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }

    /**
     * Returns a string representation of this deadline task for saving to file.
     *
     * @return A formatted string with type, status, description, and deadline.
     */
    @Override
    public String toSave() {
        return "D | " + super.toSave()
                + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
