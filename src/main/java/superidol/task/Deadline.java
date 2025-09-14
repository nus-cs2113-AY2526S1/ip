package superidol.task;

public class Deadline extends Task{
    private String time;

    public Deadline(String task, String time) {
        super(task);
        this.time = time;
    }

    public Deadline(String task, String time, boolean isDone) {
        super(task);
        this.time = time;
        this.isDone = isDone;
    }

    public String getTask() {
        return "[D]" + super.getTask() + " (by: " + time +")";
    }

    public String saveData() {
        String data = "D|";
        if (this.isDone) {
            data += "1|";
        } else {
            data += "0|";
        }
        data += this.task + "|";
        data += this.time;
        return data;
    }
}
