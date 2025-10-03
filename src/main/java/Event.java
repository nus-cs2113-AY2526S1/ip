public class Event extends Task {
    protected String start_dateTime;
    protected String end_dateTime;

    public Event(String description, String start_dateTime, String end_dateTime){
        super(description);
        this.start_dateTime = start_dateTime;
        this.end_dateTime = end_dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start_dateTime + " to: " + end_dateTime + ")";
    }
}