package espresso.datahandler;

/**
 * Represents a parsed line from the task data file.
 * Encapsulates task type, completion status, description, and any additional metadata.
 * Includes validation logic to ensure structural integrity of parsed input.
 */
public class ParsedLine {
    private String taskType;
    private boolean marked;
    private String description;
    private String[] remainder;

    private boolean isValidTaskType;
    private boolean isValidMarked;
    private boolean isValidDescription;

    /**
     * Constructs a {@code ParsedLine} object by validating and extracting components.
     *
     * @param taskType    the type of task ("T", "D", or "E")
     * @param marked      the completion status ("1" for done, "0" for not done)
     * @param description the task description
     * @param remainder   additional metadata (e.g., deadline or event timing)
     */
    public ParsedLine(String taskType, String marked, String description, String remainder) {
        validateTaskType(taskType);
        validateMarked(marked);
        validateDescription(description);
        this.taskType = taskType;
        this.marked = marked.equals("1");
        this.description = description;
        this.remainder = remainder.split("\\|");
    }

    /**
     * Checks whether all components of the parsed line are valid.
     *
     * @return {@code true} if task type, marked status, and description are valid
     */
    public boolean isFullyValid() {
        return isValidTaskType && isValidMarked && isValidDescription;
    }

    /** @return the task type code ("T", "D", or "E") */
    public String getTaskType() {
        return taskType;
    }

    /** @return {@code true} if the task is marked as completed */
    public boolean getMarked() {
        return marked;
    }

    /** @return the task description */
    public String getDescription() {
        return description;
    }

    /** @return array of additional metadata (e.g., deadline or event timing) */
    public String[] getRemainder() {
        return remainder;
    }

    /** @param description sets the task description */
    public void setDescription(String description) {
        this.description = description;
    }

    /** @param remainder sets the metadata array */
    public void setRemainder(String[] remainder) {
        this.remainder = remainder;
    }

    /** @param isValidTaskType sets the validity flag for task type */
    public void setValidTaskType(boolean isValidTaskType) {
        this.isValidTaskType = isValidTaskType;
    }

    /** @param isValidMarked sets the validity flag for marked status */
    public void setValidMarked(boolean isValidMarked) {
        this.isValidMarked = isValidMarked;
    }

    /** @param isValidDescription sets the validity flag for description */
    public void setValidDescription(boolean isValidDescription) {
        this.isValidDescription = isValidDescription;
    }

    /**
     * Validates the task type.
     * Accepts only single-character codes: "T", "D", or "E".
     *
     * @param taskType the task type code to validate
     */
    public void validateTaskType(String taskType) {
        this.isValidTaskType = taskType != null &&
                taskType.length() == 1 &&
                (taskType.equals("T") || taskType.equals("E") || taskType.equals("D"));
    }

    /**
     * Validates the marked status.
     * Accepts only "1" (done) or "0" (not done).
     *
     * @param marked the marked status to validate
     */
    public void validateMarked(String marked) {
        this.isValidMarked = marked != null &&
                marked.length() == 1 &&
                (marked.equals("1") || marked.equals("0"));
    }

    /**
     * Validates the task description.
     * Must be non-null and non-empty.
     *
     * @param description the description to validate
     */
    public void validateDescription(String description) {
        this.isValidDescription = description != null && !description.isEmpty();
    }
}