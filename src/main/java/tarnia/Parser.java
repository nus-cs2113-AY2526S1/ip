package tarnia;

public class Parser {
    public static Task parseToDo(String input) {
        return new Task(input.trim());
    }

    public static Deadline parseDeadline(String input) {

        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("empty");
        } else if (!input.contains("/by")) {
            throw new IllegalArgumentException("badformat");
        }

        String[] parts = input.split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new Deadline(description, by);
    }

    public static Event parseEvents(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("empty");
        } else if (!input.contains("/from") && !input.contains("/to")) {
            throw new IllegalArgumentException("badformat");
        }
        
        String[] parts = input.split("/from|/to", 3);
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        return new Event(description, from, to);
    }
    
    public static Task parseSavedTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new Task(description);
            break;
        case "D":
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markDone();
        }
        return task;
    }
}