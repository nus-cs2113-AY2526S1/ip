import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which contains a task description and a deadline
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String taskDescription, LocalDate deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public void printTask() {
        System.out.print("[D]");
        super.printTask();
        System.out.println(" (by:" + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}
