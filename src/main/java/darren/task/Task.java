package darren.task;

public abstract class Task {
    public String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String toStorage();

    public static Task fromStorage(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null;

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length >= 4) {
                    task = new Deadline(description, parts[3].trim());
                } else {
                    return null;
                }
                break;
            case "E":
                if (parts.length >= 5) {
                    task = new Event(description, parts[3].trim(), parts[4].trim());
                } else {
                    return null;
                }
                break;
            default:
                return null;
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
