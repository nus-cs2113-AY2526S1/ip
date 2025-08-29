final class Task {
    private String name = "";
    private boolean isDone = false;

    Task() {
    }

    Task(final String name) {
        this.name = name;
    }

    char getStatusIcon() {
        return this.isDone ? 'X' : ' ';
    }

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
