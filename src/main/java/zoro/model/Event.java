package zoro.model;

/**
 * Represents an event task with start and end times.
 * Extends the basic Task class to include time period information.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Constructs a new Event with description and time period.
     *
     * @param description - the description of the event
     * @param startTime - the start time of the event
     * @param endTime - the end time of the event
     */
    public Event(String description, String startTime, String endTime){
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskType = "[E]";
    }

    /**
     * Returns a string representation of the event including time period.
     *
     * @return - formatted string showing event details with start and end times
     */
    @Override
    public String toString() {
        return super.toString() + " (from: "  + startTime + ", to: " + endTime + ")";
    }
}
