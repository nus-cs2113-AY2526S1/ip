package michael.TaskList;

/**
 * This class handles the creation of Deadline type tasks.
 * Deadline tasks override methods to customize how task data
 * is formatted for file writing and user display.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline tasks, where Deadline is a subclass of Task.
     *
     * @param description  Description of the deadline task
     * @param by           Deadline time or date for the task
     * @param dataFilePath Location where the deadline task data will be written to
     * @param index        Current index of the task
     * @param isTaskDone   Boolean value to show if the task has already been marked as completed
     * @param isDataNew    Boolean value to show if the current task is newly created
     *                     or if it already exists in the data file
     */
    public Deadline(String description, String by, String dataFilePath, int index, boolean isTaskDone, boolean isDataNew) {
        super(description, index, "D", isTaskDone);
        this.by = by;
        if (isDataNew) {
            writeFile(dataFilePath);
        }
    }

    /**
     * Method to create a formatted string that represents how
     * a Deadline task will be stored in the tasks array.
     * It extends the base Task format by appending the deadline.
     *
     * @return String formatted for file storage of Deadline task
     */
    @Override
    public String writeFileString() {
        return super.writeFileString() + " | " + by;
    }

    /**
     * Converts the Deadline task into a formatted string representation
     * specifically for user display. Includes the deadline information.
     *
     * @return Human-readable string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
