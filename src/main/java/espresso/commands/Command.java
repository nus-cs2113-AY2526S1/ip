package espresso.commands;

/**
 * Represents a parsed command from user input.
 * Stores the command type, description, and any additional arguments
 * such as task index, deadline, event timing, or keywords.
 */
public class Command {
    private final CommandType type;
    private final String description;
    private int taskIndex = -1;
    private String deadline = "";
    private String eventStart = "";
    private String eventEnd = "";
    private String[] keywords;

    /**
     * Constructs a {@code Command} with the specified type and description.
     *
     * @param type        the type of command
     * @param description the raw description or arguments from user input
     */
    public Command(CommandType type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Returns the type of this command.
     *
     * @return the command type
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Returns the description or arguments associated with this command.
     *
     * @return the command description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task index associated with this command.
     *
     * @return the task index, or -1 if not set
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Sets the task index for this command.
     *
     * @param index the index of the task
     */
    public void setTaskIndex(int index) {
        this.taskIndex = index;
    }

    /**
     * Returns the deadline string associated with this command.
     *
     * @return the deadline string
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline string for this command.
     *
     * @param deadline the deadline value
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns the start time of an event associated with this command.
     *
     * @return the event start time
     */
    public String getEventStart() {
        return eventStart;
    }

    /**
     * Sets the start time of an event for this command.
     *
     * @param start the event start time
     */
    public void setEventStart(String start) {
        this.eventStart = start;
    }

    /**
     * Returns the end time of an event associated with this command.
     *
     * @return the event end time
     */
    public String getEventEnd() {
        return eventEnd;
    }

    /**
     * Sets the end time of an event for this command.
     *
     * @param end the event end time
     */
    public void setEventEnd(String end) {
        this.eventEnd = end;
    }

    /**
     * Returns the array of keywords used in a FIND command.
     *
     * @return the keywords array
     */
    public String[] getKeywords() {
        return this.keywords;
    }

    /**
     * Sets the keywords for a FIND command.
     *
     * @param keywords the array of keywords
     */
    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks if this command is of type {@code INVALID}.
     *
     * @return {@code true} if the command type is INVALID, {@code false} otherwise
     */
    public boolean isInvalid() {
        return this.getType() == CommandType.INVALID;
    }
}
