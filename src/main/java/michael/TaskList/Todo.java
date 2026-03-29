package michael.TaskList;

/**
 * This class handles the creation of Todo type tasks.
 * Event tasks override methods to customize how task data
 * is formatted for file writing and user display.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo tasks, where todo is a subclass of Task
     *
     * @param description  Description of task that has been parsed to remove the instruction ("todo")
     * @param dataFilePath Location where the new data will be written to
     * @param index        Current index of task
     * @param isTaskDone   Boolean value to show if the task has been marked
     * @param isDataNew    Boolean value to show if the current task is new or if it is already in the data file
     */
    public Todo(String description, String dataFilePath, int index, boolean isTaskDone, boolean isDataNew) {

        super(description, index, "T", isTaskDone);
        if (isDataNew) {
            writeFile(dataFilePath);
        }
    }

    /**
     * Method to create a formatted string that represents how
     * a Todo task will be stored in the tasks array file.
     *
     * @return String formatted for file storage of Todo task
     */
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}
