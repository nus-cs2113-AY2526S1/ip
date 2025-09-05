package superidol.task;

public class Event extends Task{
    private String startTime;
    private String endTime;

    public Event(String task, String startTime, String endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTask() {
        return "[E]" + super.getTask() +
                " (from: " + startTime + " to: " + endTime + ")";
    }
}
