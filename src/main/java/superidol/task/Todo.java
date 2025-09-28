package superidol.task;

public class Todo extends Task{
    public Todo(String task) {
        super();
        this.task = task;
    }

    public Todo(String task, boolean isDone) {
        super();
        this.task = task;
        this.isDone = isDone;
    }

    public String getTask() {
        return "[T]" + super.getTask();
    }

    public String saveData() {
        String data = "T|";
        if (this.isDone) {
            data += "1|";
        } else {
            data += "0|";
        }
        data += this.task;
        return data;
    }
}
