package Chauncey.task;

import Chauncey.exception.ChaunceyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Deadline extends Task{
    private LocalDateTime deadline;
    private static final char LABEL = 'D';
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.US);
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.US);

    /**
     * Constructor of Deadline class.
     * Turn the date information in deadline from String object to LocalDateTime object.
     *
     * @param description String of task description.
     * @param deadline Task deadline in the format of String.
     * @throws DateTimeParseException If the date in deadline is not in desired format.
     * @throws ChaunceyException If the deadline is not in desired format.
     */
    public Deadline(String description, String deadline) throws DateTimeParseException, ChaunceyException {
        super(description);
        if (!deadline.contains("by")) {
            throw new ChaunceyException("Please follow the input details format: <description> / by <yyyy-MM-dd HHmm>.");
        }
        String deadlineDate = deadline.substring(3);
        this.deadline = LocalDateTime.parse(deadlineDate, INPUT_FORMATTER);
    }

    /**
     * Returns the time of deadline in the format of <MMM dd yyyy HH:mm>
     * @return The time of deadline in specific output format.
     */
    public String getDeadline() {
        return deadline.format(OUTPUT_FORMATTER);
    }

    @Override
    /**
     * Outputs the details of the task in a specific format.
     */
    public void outputTaskDetails() {
        System.out.println("[" + LABEL + "][" + super.getStatusIcon() + "] " + super.getDescription() + " (by " + getDeadline() + ")");
    }

    @Override
    /**
     * Returns the details of the task in a specific format.
     */
    public String getTaskDetails() {
        return "[" + LABEL + "][" + super.getStatusIcon() + "] " + super.getDescription() + " (by " + getDeadline() + ")";
    }

    @Override
    /**
     * Returns a formatted String to be written to the storage file.
     */
    public String writeToFile() {
        int isDoneInInteger = getStatus()? 1 : 0;
        return LABEL + " | " + isDoneInInteger + " | " + getDescription() + " | by " + deadline.format(INPUT_FORMATTER);
    }
}
