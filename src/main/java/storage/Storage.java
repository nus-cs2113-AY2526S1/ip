package storage;

import exceptions.PepException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles saving and loading of tasks to and from the hard disk.
 * Uses a relative, OS-independent file path for portability.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath the relative path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }
    /**
     * Loads tasks from the storage file into a TaskList.
     * Creates the file and directory if they do not exist.
     * Skips corrupted lines gracefully.
     *
     * @return a TaskList containing all successfully loaded tasks
     * @throws PepException if an I/O error occurs while reading the file
     */
    public TaskList load() throws PepException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            System.out.println("[DEBUG] Loading from: " + filePath.toAbsolutePath());

            // If file doesn't exist, create folder + file, return empty list
            if (!Files.exists(filePath)) {
                createFileAndDirectory();
                return new TaskList(tasks);
            }

            // Read file line-by-line
            try (Scanner scanner = new Scanner(filePath.toFile())) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty()) {
                        continue; // skip blank lines
                    }
                    try {
                        Task task = parseTask(line);
                        tasks.add(task);
                    } catch (PepException e) {
                        // Skip corrupted line but log it
                        System.err.println("[WARN] Skipping corrupted line: " + line);
                    }
                }
            }

            return new TaskList(tasks);

        } catch (IOException e) {
            throw new PepException("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Saves the given TaskList to the storage file.
     *
     * @param tasks the TaskList to save
     * @throws PepException if an I/O error occurs while writing to the file
     */
    public void save(TaskList tasks) throws PepException {
        try {
            createFileAndDirectory();
            FileWriter fw = new FileWriter(filePath.toFile());
            for (Task task : tasks.tasks()) {
                fw.write(formatTask(task) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new PepException("Error saving tasks: " + e.getMessage());
        }
    }

    private void createFileAndDirectory() throws IOException {
        File file = filePath.toFile();
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                System.err.println("[WARN] Failed to create directory: " + parentDir);
            }
        }
        if (!file.exists()) {
            boolean created = file.createNewFile();
            if (!created) {
                System.err.println("[WARN] File already exists but was expected to be new: " + file);
            }
        }
    }

    private Task parseTask(String line) throws PepException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            Task todo = new Todo(description);
            if (isDone) todo.markAsDone();
            return todo;

        case "D":
            String by = parts[3]; // e.g., 2019-12-02
            Task deadline = new Deadline(description, by);
            if (isDone) deadline.markAsDone();
            return deadline;

        case "E":
            String from = parts[3]; // e.g., 2019-12-02T14:00
            String to = parts[4];   // e.g., 2019-12-02T16:00
            Task event = new Event(description, from, to);
            if (isDone) event.markAsDone();
            return event;

        default:
            throw new PepException("Corrupted task type: " + type);
        }
    }

    private String formatTask(Task task) {
        String status = task.isDone() ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + status + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + status + " | " + d.getDescription() + " | " + d.getBy();
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + status + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
        }
        return "";
    }
}