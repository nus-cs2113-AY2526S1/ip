package Chauncey.task;

import Chauncey.exception.ChaunceyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Event extends Task{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static final char LABEL = 'E';
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.US);
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.US);

    public Event(String description, String startTime, String endTime) throws DateTimeParseException, ChaunceyException {
        super(description);
        if (!startTime.contains("from") || !endTime.contains("to")) {
            throw new ChaunceyException("Please follow the input details format: <description> / from <yyyy-MM-dd HHmm> / to <yyyy-MM-dd HHmm>.");
        }
        String startTimeDate = startTime.substring(5);
        String endTimeDate = endTime.substring(3);
        this.startTime = LocalDateTime.parse(startTimeDate, INPUT_FORMATTER);
        this.endTime = LocalDateTime.parse(endTimeDate, INPUT_FORMATTER);
    }

    public String getStartTime() {
        return startTime.format(OUTPUT_FORMATTER);
    }

    public String getEndTime() {
        return endTime.format(OUTPUT_FORMATTER);
    }

    @Override
    public void outputTaskDetails() {
        System.out.println("[" + LABEL + "][" + super.getStatusIcon() + "] " + super.getDescription() + " (from " + getStartTime() + " to " + getEndTime() + ")");
    }

    @Override
    public String getTaskDetails() {
        return "[" + LABEL + "][" + super.getStatusIcon() + "] " + super.getDescription() + " (from " + getStartTime() + " to " + getEndTime() + ")";
    }

    @Override
    public String writeToFile() {
        int isDoneInInteger = getStatus()? 1 : 0;
        return LABEL + " | " + isDoneInInteger + " | " + getDescription() + " | from " + startTime.format(INPUT_FORMATTER) + " | to " + endTime.format(INPUT_FORMATTER);
    }
}
