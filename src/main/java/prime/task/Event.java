package prime.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + getTickUnTickIcon() + " " + super.description + " (from: " + this.from + " to: " + this.to + ")";
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }
}
