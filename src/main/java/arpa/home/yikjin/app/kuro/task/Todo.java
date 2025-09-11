package arpa.home.yikjin.app.kuro.task;

public final class Todo extends Task {
    public Todo(final String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
