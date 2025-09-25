package espresso.datahandler;
import espresso.tasks.Deadline;
import espresso.tasks.Event;
import espresso.tasks.InvalidTask;
import espresso.tasks.Task;
import espresso.tasks.Todo;

/**
 * Parses serialized task lines from a file into structured {@code Task} objects.
 * Supports multiple task types including {@code Todo}, {@code Deadline}, and {@code Event}.
 */
public class FileParser {

    /**
     * Converts an array of serialized task lines into an array of {@code Task} objects.
     *
     * @param lines each line represents a task in serialized format
     * @return array of parsed {@code Task} objects
     */
    public static Task[] parseTasks(String[] lines) {
        Task[] tasks = new Task[lines.length];
        for (int i = 0; i < lines.length; i++) {
            tasks[i] = parseTask(lines[i]);
        }
        return tasks;
    }

    /**
     * Parses a single line of task data into a {@code ParsedLine} object.
     * Handles splitting, trimming, and extracting optional metadata.
     *
     * @param line raw task line from file
     * @return structured {@code ParsedLine} containing task components
     */
    private static ParsedLine parseLine(String line) {
        String[] lineParts = line.split("\\|");
        if (lineParts.length < 3) {
            return new ParsedLine("I", "", "", "");
        }
        String taskType = lineParts[0].strip();
        String marked = lineParts[1].strip();
        String description = lineParts[2].strip();

        String remainder = "";
        if (lineParts.length > 3) {
            int remainderIndex = line.indexOf(description) + description.length() + 2;
            remainder = line.substring(remainderIndex).strip();
        }

        return new ParsedLine(taskType, marked, description, remainder);
    }

    /**
     * Converts a parsed line into a specific {@code Task} subtype.
     * Returns {@code InvalidTask} if the line is malformed or unsupported.
     *
     * @param line raw task line from file
     * @return a {@code Task} object representing the parsed data
     */
    private static Task parseTask(String line) {
        ParsedLine parsedLine = parseLine(line);
        if (!parsedLine.isFullyValid()) {
            return new InvalidTask();
        }
        String[] remainder = parsedLine.getRemainder();

        switch(parsedLine.getTaskType()) {
        case "E":
            String from = remainder[0];
            String to = remainder[1];
            return new Event(parsedLine.getDescription(), from, to, parsedLine.getMarked());
        case "D":
            String by = remainder[0];
            return new Deadline(parsedLine.getDescription(), by, parsedLine.getMarked());
        case "T":
            return new Todo(parsedLine.getDescription(), parsedLine.getMarked());
        default:
            return new InvalidTask();
        }
    }
}
