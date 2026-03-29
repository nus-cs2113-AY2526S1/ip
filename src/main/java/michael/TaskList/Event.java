package michael.TaskList;

/**
 * This class handles the creation of Event type tasks.
 * Event tasks override methods to customize how task data
 * is formatted for file writing and user display.
 */

public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor for Event tasks, where Event is a subclass of Task.
     *
     * @param description  Description of the event task
     * @param from         Start time of the event
     * @param to           End time of the event
     * @param dataFilePath Location where the event task data will be written to
     * @param index        Current index of the task
     * @param isTaskDone   Boolean value to show if the task has already been marked as completed
     * @param isDataNew    Boolean value to show if the current task is newly created
     *                     or if it already exists in the data file
     */
    public Event(String description, String from, String to, String dataFilePath, int index, boolean isTaskDone, boolean isDataNew) {
        super(description, index, "E", isTaskDone);
        this.from = from;
        this.to = to;
        if (isDataNew) {
            writeFile(dataFilePath);
        }
    }

    /**
     * Method to create a formatted string that represents how
     * an Event task will be stored in the data file.
     * It extends the base Task format by appending start and end times.
     *
     * @return String formatted for file storage of Event task
     */
    @Override
    public String writeFileString() {
        return super.writeFileString() + " | " + from + "-" + to;
    }

    /**
     * Method to create a formatted string that represents how
     * a Event task will be stored in the tasks array.
     * It extends the base Task format by appending the duration.
     *
     * @return String formatted for file storage of Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
