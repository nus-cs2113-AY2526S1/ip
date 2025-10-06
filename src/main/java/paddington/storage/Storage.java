package paddington.storage;

import paddington.task.*;
import paddington.ui.PaddingtonException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the saving and loading of task data to and from a file.
 * The {@link Storage} class provides methods for initialising, loading and saving tasks to a persistent file system.
 * The tasks are stored in a file named {@code PaddingtonData.txt}.
 */
public class Storage {

    private static final String DATA_FILE_PATH = "./data/PaddingtonData.txt";

    /**
     * Reads a task from a saved string representation.
     * The line is parsed based on the task type (Todo, Deadline, Event).
     *
     * @param line the saved string representation of a task
     * @return the corresponding task object
     * @throws PaddingtonException if the task format is invalid
     */
    private static Task readFromSave(String line) throws PaddingtonException {
        char taskType = line.charAt(0);

        return switch (taskType) {
            case 'T' -> Todo.formatFromSave(line);
            case 'D' -> Deadline.formatFromSave(line);
            case 'E' -> Event.formatFromSave(line);
            default -> throw new PaddingtonException("Invalid task format.");
        };
    }

    /**
     * Initialises the storage by creating the necessary file and directories if they do not exist.
     * Creates save file if an existing one is not found.
     *
     * @throws IOException if an I/O error occurs while creating directories or the file
     */
    public static void init() throws IOException {
        File file = new File(DATA_FILE_PATH);

        // Found save file.
        if (file.exists()) {
            System.out.println("Log: Found save file.");
            return;
        }

        // Else, create save file.
        File parentDir = file.getParentFile();
        if (parentDir.mkdirs()) {
            System.out.println("Log: Created directory at " + parentDir);
        }
        if (file.createNewFile()) {
            System.out.println("Log: Created new save file at " + file.getPath());
        }
    }

    /**
     * Loads the saved tasks from the storage file and adds them to the task list.
     * Each line in the file is parsed to create a task and add it to the task list.
     *
     * @throws PaddingtonException if there is an error while loading the tasks
     */
    public static void load() throws PaddingtonException {
        File file = new File(DATA_FILE_PATH);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = readFromSave(line);
                TaskList.addTaskSilently(task);
            }
        } catch (FileNotFoundException e) {
            throw new PaddingtonException("Error loading saved data file.");
        }
    }

    /**
     * Saves the current list of tasks to the storage file.
     * Each task is converted to its string format and written to the file.
     *
     * @param tasks the list of tasks to save
     * @throws PaddingtonException if there is an error while saving the tasks
     */
    public static void save(ArrayList<Task> tasks) throws PaddingtonException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(DATA_FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.formatToSave() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new PaddingtonException("Error saving file. " + e);
        }
    }
}
