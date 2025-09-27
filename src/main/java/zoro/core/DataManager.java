package zoro.core;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import zoro.model.Task;
import zoro.model.Deadline;
import zoro.model.Event;

/**
 * Manages data persistence for tasks.
 * Handles saving and loading tasks to/from file storage.
 */
public class DataManager {
    private static final String DATA_DIRECTORY = "data";
    private static final String SAVE_FILENAME = "zoro.txt";
    private final Path dataPath;

    /**
     * Constructs a new DataManager and ensures data directory exists.
     */
    public DataManager() {
        this.dataPath = Paths.get(DATA_DIRECTORY, SAVE_FILENAME);
        createFileIfNotExists();
    }

    /**
     * Creates the data directory if it doesn't exist.
     * Handles IOException by printing error message.
     */
   private void createFileIfNotExists() {
        try {
            Path pathDirectory = Paths.get(DATA_DIRECTORY);
            if (!Files.exists(pathDirectory)) {
                Files.createDirectories(pathDirectory);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
   }

    /**
     * Saves the current list of tasks to file.
     * Each task is written as a separate line using its toString() method.
     *
     * @param tasks - the list of tasks to save
     */
   public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(dataPath)) {
            for (Task task : tasks) {
                String line = task.toString();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Save Task Error: " + e.getMessage());
            e.printStackTrace();
        }
   }


    /**
     * Loads tasks from the save file.
     * Returns empty list if file doesn't exist or if there are parsing errors.
     *
     * @return - list of tasks loaded from file
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();

        if (!Files.exists(dataPath)) {
            return tasks;
        }

        try (BufferedReader reader = Files.newBufferedReader(dataPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = stringToTask(line.trim());
                if  (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Load Task Error: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Converts a string representation back to a Task object.
     * Parses the saved format to recreate tasks with proper type and status.
     *
     * @param line - the string representation of a task
     * @return - the reconstructed Task object, or null if parsing fails
     */
    private Task stringToTask(String line) {
        if (line.isEmpty()) {
            return null;
        }

        try {
            // [T] [ ] description
            // [D] [ ] description (by: deadline)
            // [E] [ ] description (from: start, to: end)

            if (line.length() < 7) { // need at least [T] [ ] x
                return null;
            }

            String taskType = line.substring(0, 3);
            boolean isDone = line.startsWith("[X]", 4);
            String remainder = line.substring(8);

            Task task = null;

            switch (taskType) {
                case "[T]": //ttodo task
                    task = new Task(remainder);
                    break;

                case "[D]": //deadline
                    int byStart = remainder.indexOf("(by: ");
                    if (byStart != -1) {
                        String description = remainder.substring(0, byStart).trim();
                        String deadline = remainder.substring(byStart + 5, remainder.length() - 1);
                        task = new Deadline(description, deadline);
                    }
                    break;

                case "[E]": //event
                    int fromStart = remainder.indexOf("(from: ");
                    int toStart = remainder.indexOf(", to: ");
                    if (fromStart != -1 && toStart != -1) {
                        String description = remainder.substring(0, fromStart).trim();
                        String startTime = remainder.substring(fromStart + 7, toStart);
                        String endTime = remainder.substring(toStart + 6, remainder.length() - 1);
                        task = new Event(description, startTime, endTime);
                    }
                    break;
            }

            if (task != null && isDone) {
                task.toggleDone();
            }

            return task;

        } catch (Exception e) {
            System.err.println("error parsing task line: " + line);
            return null;
        }
    }
}

