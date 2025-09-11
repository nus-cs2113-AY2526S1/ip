package arpa.home.yikjin.app.kuro.task;

public final class Deadline extends Task {
    private final String due;

    public Deadline(final String name, final String due) {
        super(name);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due + ")";
    }
}
