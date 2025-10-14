/**
 * Represents an Event task which contains a task description, a start date and an end date
 */
public class Event extends Task {
    protected String startDate;
    protected String endDate;

    public Event(String taskDescription, String startDate, String endDate) {
        super(taskDescription);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void printTask() {
        System.out.print("[E]");
        super.printTask();
        System.out.println(" (from:" + this.startDate + " to " + this.endDate + ")");
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
