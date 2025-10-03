import java.util.ArrayList;

public record TaskList(ArrayList<Task> tasks) {

    public void add(Task t) {
        tasks.add(t);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }
}