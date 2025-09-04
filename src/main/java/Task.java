final class Task {
    private String name;
    private boolean isDone;

    Task(final String name) {
        this.name = name;
        this.isDone = false;
    }

    char getStatusIcon() {
        return this.isDone ? 'X' : ' ';
    }

    @Override
    public String toString() {
        return this.name + (this.isDone ? " (done)" : "");
    }

    String getName() {
        return this.name;
    }

    void setName(final String name) {
        this.name = name;
    }

    boolean isDone() {
        return this.isDone;
    }

    void setDone(final boolean done) {
        this.isDone = done;
    }
}
