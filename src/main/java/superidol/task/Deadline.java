package superidol.task;

public class Deadline extends Task{
    private String time;

    public Deadline(String task, String time) {
        super(task);
        this.time = time;
    }

    public String getTask() {
        return "[D]" + super.getTask() + " (by: " + time +")";
    }

}
