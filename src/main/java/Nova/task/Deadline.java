package Nova.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a Deadline task which has description
 * and a specific date and time by which it should be completed.
 */

public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a {@code Deadline} with the specified description and due date/time.
     *
     * @param description the task description
     * @param by the due date and time as a string in the format "d/M/yyyy HHmm" (e.g., "1/10/2025 1800")
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns a string representation of the deadline task for display.
     * <p>
     * Example: [D][ ] return book (by: Oct 1 2025 1800)
     *
     * @return formatted string representing the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + ")";
    }

    /**
     * Returns the due date/time as a string formatted for saving in the data file.
     * <p>
     * Format: "dd/MM/yyyy HHmm" (e.g., "01/10/2025 1800")
     *
     * @return formatted due date/time string
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}

