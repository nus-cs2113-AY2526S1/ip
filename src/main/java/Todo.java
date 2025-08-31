public class Todo extends Task{
    public Todo(String task) {
        super(task);
    }

    public String getTask() {
        return "[T]" + super.getTask();
    }
}
