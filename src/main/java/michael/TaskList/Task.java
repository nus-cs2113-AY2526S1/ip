package michael.TaskList;

import java.io.IOException;

import static michael.Storage.Storage.appendToFile;

/**
 * This class represents a generic Task object.
 * It stores the description, status, type, and index of a task,
 * and provides methods to manipulate and format task data.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected int taskIndex;

    /**
     * Getter for the description of the task.
     *
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the index of the task.
     *
     * @return Integer index of the task
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Constructor for the Task class.
     *
     * @param description Description of the task
     * @param index       Current index of the task
     * @param taskType    Type of the task (e.g. Todo, Deadline, Event)
     * @param isTaskDone  Boolean indicating if the task is already completed
     */
    public Task(String description, int index, String taskType, boolean isTaskDone) {
        this.description = description;
        this.isDone = isTaskDone;
        this.taskType = taskType;
        this.taskIndex = index;
    }

    /**
     * Creates a formatted string version of the task that is
     * suitable for writing to a data file.
     *
     * @return Formatted string representation of the task
     */
    public String writeFileString() {
        return taskIndex + ". " + taskType + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Writes the current task data into the given file.
     *
     * @param dataFile The path to the file where task data will be stored
     */
    public void writeFile(String dataFile) {
        String taskString = writeFileString();
        appendToFile(dataFile, taskString + System.lineSeparator());
    }

    /**
     * Returns the status icon of the task.
     * "X" if completed, otherwise a blank space.
     *
     * @return Status icon string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the current task as completed (isDone = true).
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the current task as not yet completed (isDone = false).
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Converts the task into a formatted string representation
     * for display to the user.
     *
     * @return Human-readable string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
