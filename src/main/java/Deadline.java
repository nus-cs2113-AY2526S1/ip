final class Deadline extends Task {
    private final String due;

    Deadline(final String name, final String due) {
        super(name);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }
}
