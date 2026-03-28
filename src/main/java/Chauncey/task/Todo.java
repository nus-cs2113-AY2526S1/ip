package Chauncey.task;

public class Todo extends Task{
    private static final char LABEL = 'T';

    public Todo(String description) {
        super(description);
    }

    @Override
    /**
     * Outputs the details of the task in a specific format.
     */
    public void outputTaskDetails() {
        System.out.println("[" + LABEL + "][" + super.getStatusIcon() + "] " + super.getDescription());
    }

    @Override
    /**
     * Returns the details of the task in a specific format.
     */
    public String getTaskDetails() {
        return "[" + LABEL + "][" + super.getStatusIcon() + "] " + super.getDescription();
    }

    @Override
    /**
     * Returns a formatted String to be written to the storage file.
     */
    public String writeToFile() {
        int isDoneInInteger = getStatus()? 1 : 0;
        return LABEL + " | " + isDoneInInteger + " | " + getDescription();
    }
}
