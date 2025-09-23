package arpa.home.yikjin.app.kuro.task;

enum TaskType {
    TODO('T'), DEADLINE('D'), EVENT('E'), INVALID(' ');

    private final char taskType;

    TaskType(final char taskType) {
        this.taskType = taskType;
    }

    static TaskType parse(final char taskType) {
        for (final TaskType enumTaskType : values()) {
            if (taskType == enumTaskType.taskType) {
                return enumTaskType;
            }
        }

        return INVALID;
    }

    char toChar() {
        return taskType;
    }

    @Override
    public String toString() {
        return String.valueOf(taskType);
    }
}
