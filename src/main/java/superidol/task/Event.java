package superidol.task;

public class Event extends Task{
    private String startTime;
    private String endTime;

    public Event(String task, String startTime, String endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String task, String startTime, String endTime, boolean isDone) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = isDone;
    }

    public String getTask() {
        return "[E]" + super.getTask() +
                " (from: " + startTime + " to: " + endTime + ")";
    }

    public String saveData() {
        String data = "E|";
        if (this.isDone) {
            data += "1|";
        } else {
            data += "0|";
        }
        data += task + "|";
        data += startTime + "|";
        data += endTime;
        return data;
    }
}
