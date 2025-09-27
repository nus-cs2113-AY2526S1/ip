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

    public Deadline(String description, String deadline) throws DateTimeParseException, ChaunceyException {
        super(description);
        if (!deadline.contains("by")) {
            throw new ChaunceyException("Please follow the input details format: <description> / by <yyyy-MM-dd HHmm>.");
        }
        String deadlineDate = deadline.substring(3);
        this.deadline = LocalDateTime.parse(deadlineDate, INPUT_FORMATTER);
    }

    public String getDeadline() {
        return deadline.format(OUTPUT_FORMATTER);
    }

    @Override
    public void outputTaskDetails() {
        System.out.println("[" + LABEL + "][" + super.getStatusIcon() + "] " + super.getDescription() + " (by " + getDeadline() + ")");
    }

    @Override
    public String getTaskDetails() {
        return "[" + LABEL + "][" + super.getStatusIcon() + "] " + super.getDescription() + " (by " + getDeadline() + ")";
    }

    @Override
    public String writeToFile() {
        int isDoneInInteger = getStatus()? 1 : 0;
        return LABEL + " | " + isDoneInInteger + " | " + getDescription() + " | by " + deadline.format(INPUT_FORMATTER);
    }
}
