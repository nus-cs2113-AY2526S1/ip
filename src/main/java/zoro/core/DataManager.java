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

   /*
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
*/

    private Task stringToTask(String line) {
        if (line.isEmpty()) {
            return null;
        }

        String[] args = line.split(" ");
        if (args.length < 3) {
            return null;
        }

        String type = args[1];
        boolean isDone = false;
        String description = args[3];

        Task task = null;

        switch (type) {
        case "[T]":
            task = new Task(description);
            break;
        }

        /*
        if ((task != null) && isDone) {
            task.toggleDone();
        }
         */

        return task;
    }

}

