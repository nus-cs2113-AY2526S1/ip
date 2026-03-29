package Siao.management;

import Siao.task.Deadline;
import Siao.task.Event;
import Siao.task.Task;
import Siao.Parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storage and retrieval of Task objects to and from a file.
 * Provides methods to save individual tasks, save all tasks, and load tasks from storage.
 */
public class Storage {
    private final File storageFile;

    /**
     * Constructs a Storage object for a given file path.
     * Ensures that the storage file exists or creates it if it does not.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Storage (String filePath) {
        this.storageFile = new File(filePath);
        ensureFileExists();
    }

    /**
     * Ensures that the storage file and its parent directories exist.
     * If the file or directories do not exist, they are created.
     */
    private void ensureFileExists() {
        try {
            File parentDir = storageFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating storage file: " + e.getMessage());
        }
    }

    public void saveTask(Task task) {
        try {
            FileWriter writer = new FileWriter(storageFile, true);
            writer.write(savedTaskToString(task) + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error creating storage file: " + e.getMessage());
        }
    }

    /**
     * Converts a Task object in the list into a string format suitable for storage.
     * The format is: "type | status | description | additional info".
     *
     * @param task the Task object to convert
     * @return a formatted string representing the task for storage
     */
    public String savedTaskToString(Task task) {
        String type = task.getType();
        String desc = task.getDescription();
        String status = task.getStatusIcon().equals("X") ? "1" : "0";

        if (task instanceof Deadline) {
            String by = ((Deadline) task).by;
            return type + " | " + status + " | " + desc + " | " + by;
        }
        if (task instanceof Event) {
            String from = ((Event) task).from;
            String to = ((Event) task).to;
            return type + " | " + status + " | " + desc + " | " + from + " - " + to;
        } else {
            return type + " | " + status + " | " + desc;
        }
    }

    /**
     * Overwrites the storage file with all tasks in the given list.
     *
     * @param tasks the list of Task objects to save
     * @throws RuntimeException if an I/O error occurs while writing to the file
     */
    public void saveAllTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(storageFile, false);
            for (Task task : tasks) {
                writer.write(savedTaskToString(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads all tasks from the storage file into an ArrayList.
     * Uses the Parser class to convert each line of the file into a Task object.
     *
     * @return an ArrayList containing all loaded Task objects
     */
    public ArrayList<Task> loadTasks(){
        ArrayList<Task> list = new ArrayList<>();

        try {
            File file = new File(Constants.FILE_PATH);
            if (!file.exists()) {
                throw new FileNotFoundException(Constants.FILE_PATH + " not found");
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTaskFromStorage(line);
                if (task != null) {
                    list.add(task);
                }
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return list;
    }

}
