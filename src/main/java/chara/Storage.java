package chara;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


import chara.task.Deadline;
import chara.task.Event;
import chara.task.Task;
import chara.task.Todo;


/**
 * Manages persistence of tasks on disk.
 * Provides methods to save tasks in a readable format and load them back,
 * with a safe variant (saveQuietly) that suppresses exceptions.
 * File format (UTF-8, '|' separated):
 *   T | 0 |read book
 *   D | 1 | return book (by: June 6th)
 *   E | 0 | project meeting (from: Aug 6th 2pm to: 4pm)
 */
public class Storage {
    private Storage() {}

    /**
     * Loads tasks from the specified file into a list.
     * Each line of the file is parsed back into the corresponding Task subclass
     * (Todo, Deadline, or Event). If the file does not exist, an empty list is
     * returned. Malformed or corrupted lines are skipped to ensure the program
     * can still start up cleanly.
     *
     * @param path the file path to read tasks from (e.g., data/chara.txt)
     * @return a list of tasks reconstructed from the file, or an empty list if the file does not exist
     * @throws IOException if an I/O error occurs during reading
     */
    public static TaskList load(Path path) throws IOException {
        TaskList tasks = new TaskList();

        // First run: no file yet → ensure parent dir exists and return empty list
        if (!Files.exists(path)) {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            return tasks;
        }

        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String line : lines) {
            if (line == null) continue;
            line = line.trim();
            if (line.isEmpty()) continue;

            try {
                // Expect: "<Type> | <0/1> | <rest>"
                String[] parts = line.split("\\|", 3);
                if (parts.length < 3) continue;

                String type = parts[0].trim();              // T / D / E
                boolean done = "1".equals(parts[1].trim()); // 1 = done, 0 = not done
                String rest = parts[2].trim();              // description + optional "(by: ...)" / "(from: ... to: ...)"

                Task t;
                switch (type) {
                case "T": {
                    // "desc"
                    String desc = rest;
                    t = new Todo(desc);
                    break;
                }
                case "D": {
                    // "desc (by: due)"
                    int open = rest.lastIndexOf("(by:");
                    int close = rest.lastIndexOf(")");
                    if (open == -1 || close == -1 || close < open) continue;

                    String desc = rest.substring(0, open).trim();
                    String by   = rest.substring(open + 4, close).trim(); // after "(by:" to before ')'
                    t = new Deadline(desc, by);
                    break;
                }
                case "E": {
                    // "desc (from: start to: end)"
                    int fromIdx = rest.indexOf("(from:");
                    int toIdx   = rest.indexOf("to:", Math.max(fromIdx, 0));
                    int close   = rest.lastIndexOf(")");
                    if (fromIdx == -1 || toIdx == -1 || close == -1 || !(fromIdx < toIdx && toIdx < close)) continue;

                    String desc = rest.substring(0, fromIdx).trim();
                    String from = rest.substring(fromIdx + 6, toIdx).trim(); // after "(from:" up to "to:"
                    String to    = rest.substring(toIdx + 3, close).trim();  // after "to:" up to ')'
                    t = new Event(desc, from, to);
                    break;
                }
                default:
                    continue; // unknown type
                }

                if (done) t.markAsDone(); else t.markAsNotDone();
                tasks.add(t);

            } catch (Exception ignoreMalformedLine) {
                // Skip malformed/corrupted lines but keep loading the rest
            }
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the specified file path.
     * Unlike {@link #save(List, Path)}, this method suppresses any thrown
     * exceptions and prints an error message instead.
     *
     * @param tasks   the list of tasks to save
     * @param savePath the path of the file to save to
     */
    public static void saveQuietly(TaskList tasks, Path savePath) {
        try {
            save(tasks, savePath);
        } catch (Exception e) {
            System.out.println("Chara: Couldn't SAVE to disk just now (it's okay, try again with more DETERMINATION!).");
        }
    }

    /**
     * Saves the current list of tasks to the specified file in a human-readable format.
     * Each task is serialized according to its type (Todo, Deadline, Event) with
     * additional information included for deadlines ("by:") and events ("from:" / "to:").
     * Automatically creates the parent directory and file if they do not exist.
     *
     * @param tasks the list of tasks to be saved
     * @param path  the file path where the task data will be written (e.g., data/chara.txt)
     * @throws IOException if an I/O error occurs during writing
     */
    public static void save(List<Task> tasks, Path path) throws IOException {
        if (path.getParent() != null) Files.createDirectories(path.getParent());

        List<String> out = new ArrayList<>(tasks.size());
        for (Task t : tasks) {
            if (t instanceof Todo) {
                out.add(String.format("T | %d | %s",
                        t.isDone() ? 1 : 0,
                        t.getDescription()));
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                out.add(String.format("D | %d | %s (by: %s)",
                        t.isDone() ? 1 : 0,
                        d.getDescription(),
                        d.getBy()));
            } else if (t instanceof Event) {
                Event e = (Event) t;
                out.add(String.format("E | %d | %s (from: %s to: %s)",
                        t.isDone() ? 1 : 0,
                        e.getDescription(),
                        e.getFrom(),
                        e.getTo()));
            }
        }

        Files.write(path, out, StandardCharsets.UTF_8);
    }
}