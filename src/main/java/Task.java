final class Task {
    private String name;

    Task() {
        this.name = "";
    }

    Task(final String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    String getName() {
        return this.name;
    }

    void setName(final String name) {
        this.name = name;
    }
}
