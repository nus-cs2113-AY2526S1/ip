package chara.task;

/**
 * Represents a task scheduled for a specific time range.
 * Stores a description along with "/from" and "/to" times.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a chara.task.Event task with the given description, start time, and end time.
     *
     * @param description description of the event
     * @param from start date/time of the event
     * @param to end date/time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String getTaskTypeIcon() {
        return "[E]";
    }

    @Override
    public void printTask() {
        System.out.print(taskTypeIcon + statusIcon + ' ' + description + " (from: " + from + " to: " + to + ")");
    }
}
