package amadeus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to save and load tasks from file.
 * It will write tasks to file or read them back.
 */
public class Storage {
    /** Path to the file where tasks are stored */
    private final String filePath;

    /**
     * Create storage with file path.
     *
     * @param filePath string path to file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save all tasks to the file.
     *
     * @param tasks list of tasks to save
     * @throws IOException if there is error writing file
     */
    public void save(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task t : tasks) {
                writer.write(t.toFileFormat());
                writer.newLine();
            }
        }
    }

    /**
     * Load tasks from the file.
     * If file not exist, return empty list.
     *
     * @return list of tasks loaded
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                if (task != null) tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error while reading files: " + e.getMessage());
        }
        return tasks;
    }
}
