package rudeus.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rudeus.ui.Ui;

/**
 * Handles saving and loading tasks to and from a local file.
 */
public class LocalSave {
    /**
     * Saves the list of tasks to a file.
     *
     * @param filePath The path to the file where tasks will be saved.
     * @param lines    The list of task strings to save.
     */
    public static void saveToFile(String filePath, List<String> lines) {
        // Ensure the data directory exists before saving
        File file = new File(filePath);
        File dataDir = file.getParentFile();
        if (dataDir != null && !dataDir.exists()) {
            dataDir.mkdirs(); // Creates the directory if it doesn't exist
        }
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            Ui.printMessageWithBorders("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from a file.
     *
     * @param filePath The path to the file from which tasks will be loaded.
     * @return A list of task strings loaded from the file.
     */
    public static List<String> loadFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return lines;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            Ui.printMessageWithBorders("Failed to load tasks: " + e.getMessage());
        }
        return lines;
    }
}

