abstract class Task {
    private final String name;
    private boolean isDone;

    Task(final String name) {
        this.name = name;
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }

    char getStatusIcon() {
        return isDone ? 'X' : ' ';
    }

    void setDone(final boolean done) {
        isDone = done;
    }
}
