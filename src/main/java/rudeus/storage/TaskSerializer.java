package rudeus.storage;

import java.util.ArrayList;
import java.util.List;

import rudeus.task.Deadline;
import rudeus.task.Event;
import rudeus.task.Task;
import rudeus.task.Todo;

/**
 * Utility class for serializing and deserializing tasks to and from strings for storage.
 */
public class TaskSerializer {
    /**
     * Serializes a list of tasks into a list of strings for storage.
     *
     * @param taskList The list of tasks to serialize.
     * @return A list of strings representing the serialized tasks.
     */
    public static List<String> serializeTasks(List<Task> taskList) {
        List<String> lines = new ArrayList<>();
        for (Task task : taskList) {
            lines.add(serializeTask(task));
        }
        return lines;
    }

    /**
     * Deserializes a list of strings into a list of tasks.
     *
     * @param lines The list of strings representing serialized tasks.
     * @return A list of deserialized Task objects.
     */
    public static List<Task> deserializeTasks(List<String> lines) {
        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            Task task = deserializeTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    private static String serializeTask(Task task) {
        String type;
        if (task instanceof Todo) {
            type = "T";
        } else if (task instanceof Deadline) {
            type = "D";
        } else if (task instanceof Event) {
            type = "E";
        } else {
            type = "?";
        }
        String status = task.getIsDone() ? "1" : "0";
        String desc = task.getDescription();
        if (task instanceof Deadline d) {
            return String.format("%s | %s | %s | %s", type, status, desc, d.toString().replace("[D]" + d, "").trim());
        } else if (task instanceof Event e) {
            return String.format("%s | %s | %s | %s", type, status, desc, e.toString().replace("[E]" + e, "").trim());
        } else {
            return String.format("%s | %s | %s", type, status, desc);
        }
    }

    private static Task deserializeTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = "1".equals(parts[1]);
            String desc = parts[2];
            switch (type) {
            case "T":
                Todo todo = new Todo(desc);
                todo.setIsDone(isDone);
                return todo;
            case "D":
                if (parts.length < 4) {
                    return null;
                }
                Deadline deadline = new Deadline(desc, parts[3]);
                deadline.setIsDone(isDone);
                return deadline;
            case "E":
                if (parts.length < 4) {
                    return null;
                }
                // Try to extract from and to from the event string
                return getEvent(parts, desc, isDone);
            default:
                return null;
            }
        } catch (Exception e) {
            // Ignore malformed lines
            return null;
        }
    }

    private static Event getEvent(String[] parts, String desc, boolean isDone) {
        String eventInfo = parts[3];
        String from = "";
        String to = "";
        int fromIdx = eventInfo.indexOf("from:");
        int toIdx = eventInfo.indexOf("to:");
        if (fromIdx != -1 && toIdx != -1) {
            from = eventInfo.substring(fromIdx + 5, toIdx).trim();
            to = eventInfo.substring(toIdx + 3).trim();
        }
        Event event = new Event(desc, from, to);
        event.setIsDone(isDone);
        return event;
    }
}

