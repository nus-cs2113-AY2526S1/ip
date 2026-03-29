package amadeus;

/**
 * Simple task without date or time.
 */
public class ToDo extends Task {

    /**
     * Create ToDo task with description.
     *
     * @param description text of task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + description;
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }
}