package amadeus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task with deadline. It must be done before specific date and time.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Create deadline task with description and date-time string.
     *
     * @param description text of task
     * @param byStr string like yyyy-MM-dd HHmm
     * @throws AmadeusException if date is wrong format
     */
    public Deadline(String description, String byStr) throws AmadeusException {
        super(description);
        try {
            this.by = LocalDateTime.parse(byStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new AmadeusException("Invalid date format. Please use yyyy-MM-dd HHmm.");
        }
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[" + getTypeIcon() + "]" + getStatusIcon() + " " + description +
                " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    /**
     * Get the deadline date and time.
     *
     * @return LocalDateTime of deadline
     */
    public LocalDateTime getBy() {
        return by;
    }
}