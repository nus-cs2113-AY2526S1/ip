package arpa.home.yikjin.app.kuro.task;

/**
 * Invalid task type
 */
public final class InvalidTask extends Task {
    /**
     * Create an invalid task
     */
    public InvalidTask() {
        super(TaskType.INVALID, "*** INVALID TASK ***");
    }
}
