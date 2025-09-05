abstract class Task {
    private final String name;
    private boolean isDone;

    Task(final String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }

    char getStatusIcon() {
        return this.isDone ? 'X' : ' ';
    }

    void setDone(final boolean done) {
        this.isDone = done;
    }
}
