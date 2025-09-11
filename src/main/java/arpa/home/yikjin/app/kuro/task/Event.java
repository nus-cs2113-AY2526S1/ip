package arpa.home.yikjin.app.kuro.task;

public final class Event extends Task {
    private final String from;
    private final String to;

    public Event(final String name, final String from, final String to) {
        super(name);

        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }
}
