package amadeus;

/**
 * Abstract class for all tasks.
 * It has description and status if done or not.
 */
public abstract class Task {
    /** Text of task */
    protected final String description;

    /** True if task is done */
    protected boolean isDone;

    /**
     * Create task with description, default not done.
     *
     * @param description text of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Set task as done */
    public void markAsDone() {
        this.isDone = true;
    }

    /** Set task as not done */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Get status icon string.
     *
     * @return "[X]" if done, "[ ]" if not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /** Return type icon of task (T, D, E) */
    public abstract String getTypeIcon();

    /** Convert task to string for printing */
    @Override
    public String toString() {
        return "[" + getTypeIcon() + "]" + getStatusIcon() + " " + description;
    }

    /**
     * Create task from file line.
     * Line format depends on task type.
     *
     * @param line string from file
     * @return Task object or null if line is corrupted
     */
    public static Task fromFileFormat(String line) {
        try {
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean done = parts[1].trim().equals("1");
            String desc = parts[2].trim();

            switch (type) {
            case "T":
                Task t = new ToDo(desc);
                if (done) t.markAsDone();
                return t;
            case "D":
                String by = parts[3].trim();
                Task d = new Deadline(desc, by);
                if (done) d.markAsDone();
                return d;
            case "E":
                String fromStr = parts[3].trim();
                String toStr = parts.length > 4 ? parts[4].trim() : "";
                Task e = new Event(desc, fromStr, toStr);
                if (done) e.markAsDone();
                return e;
            }
        } catch (Exception e) {
            System.out.println("⚠️ L corrupted line : " + line);
        }
        return null;
    }

    /** Convert task to line for file saving */
    public abstract String toFileFormat();
}
