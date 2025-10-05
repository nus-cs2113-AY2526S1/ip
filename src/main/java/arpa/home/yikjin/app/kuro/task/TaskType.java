package arpa.home.yikjin.app.kuro.task;

/**
 * Task type enum
 */
public enum TaskType {
    TODO('T'), DEADLINE('D'), EVENT('E'), INVALID(' ');

    private final char taskType;

    /**
     * Create a task type enum
     *
     * @param taskType Character to represent task type in file
     */
    TaskType(final char taskType) {
        this.taskType = taskType;
    }

    /**
     * Convert the task type character into the task type enum
     *
     * @param taskType Task type character in file
     *
     * @return Task type enum
     */
    public static TaskType parse(final char taskType) {
        for (final TaskType enumTaskType : values()) {
            if (taskType == enumTaskType.taskType) {
                return enumTaskType;
            }
        }

        return INVALID;
    }

    /**
     * Convert the task type enum to the character used to represent it
     *
     * @return Character to represent task type in file
     */
    char toChar() {
        return taskType;
    }

    /**
     * Convert the task type enum to the character string used to represent it
     *
     * @return Character string to represent task type in file
     */
    @Override
    public String toString() {
        return String.valueOf(taskType);
    }
}
