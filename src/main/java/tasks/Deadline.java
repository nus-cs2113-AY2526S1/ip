package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} stores a description and a due date.
 * <p>
 * Input dates are expected in the format {@code yyyy-MM-dd}
 * (e.g., {@code 2019-10-15}). When displayed, the date is formatted
 * in a more user-friendly style such as {@code Oct 15 2019}.
 * </p>
 */
public class Deadline extends Task {
    /** The due date of the deadline task. */
    protected LocalDate by;

    /**
     * Constructs a {@code Deadline} with the specified description and due date.
     *
     * @param description the description of the deadline task
     * @param by the due date in {@code yyyy-MM-dd} format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the due date of this deadline.
     *
     * @return the due date as a {@link LocalDate}
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns the string representation of this deadline task,
     * including its description, completion status, and formatted due date.
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}