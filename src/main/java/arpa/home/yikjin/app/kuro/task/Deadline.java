package arpa.home.yikjin.app.kuro.task;

public final class Deadline extends Task {
    private final String due;

    public Deadline(final String name, final String due) {
        super(TaskType.DEADLINE, name);
        this.due = due;
    }

    public String getDue() {
        return due;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), due);
    }
}
