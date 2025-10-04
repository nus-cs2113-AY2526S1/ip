package arpa.home.yikjin.app.kuro.task;

public final class Event extends Task {
    private final String from;
    private final String to;

    public Event(final String name, final String from, final String to) {
        super(TaskType.EVENT, name);

        this.from = from;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s, to: %s)", super.toString(), from, to);
    }
}
