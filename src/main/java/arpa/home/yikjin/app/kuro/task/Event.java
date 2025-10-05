package arpa.home.yikjin.app.kuro.task;

/**
 * Event task type
 */
public final class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Create an event task
     *
     * @param name Title of event task to create
     * @param from When the event starts from
     * @param to   When the event lasts till
     */
    public Event(final String name, final String from, final String to) {
        super(TaskType.EVENT, name);

        this.from = from;
        this.to = to;
    }

    /**
     * Get when the event lasts till
     *
     * @return When the event lasts till
     */
    public String getTo() {
        return to;
    }

    /**
     * Get when the event starts from
     *
     * @return When the event starts from
     */
    public String getFrom() {
        return from;
    }

    /**
     * Get the string representation of the event task
     *
     * @return String representation of the event task
     */
    @Override
    public String toString() {
        return String.format("%s (from: %s, to: %s)", super.toString(), from, to);
    }
}
