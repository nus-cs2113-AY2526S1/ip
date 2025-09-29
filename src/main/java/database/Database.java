package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

/**
 * Handles the loading and saving of tasks to a file.
 * This class ensures all file-related operations are centralised,
 * keeping the rest of the application's logic clean.
 */
public class Database {
    private final String filePath;

    /**
     * Constructs a Database object with the specified file path.
     * @param filePath The path to the file where tasks are stored.
     */
    public Database(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     * @return An ArrayList of Task objects loaded from the file.
     * @throws FileNotFoundException If the file at the specified path does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            taskList.add(parseTask(line));
        }

        scanner.close();
        return taskList;
    }

    /**
     * Saves the list of tasks to the file.
     * @param taskList The TaskList object containing the tasks to be saved.
     * @throws IOException If an error occurs during file writing.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < taskList.getCount(); i++) {
            writer.write(taskList.getTask(i).toSaveString() + "\n");
        }
        writer.close();
    }

    /**
     * Parses a single line from the file and creates the corresponding Task object.
     * @param line The line of text from the file.
     * @return The parsed Task object.
     */
    public Task parseTask(String line) {
        String[] parts = line.split("\\|");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String taskDescription = parts[2];

        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(taskDescription);
            break;
        case "D":
            String deadline = parts[3];
            task = new Deadline(taskDescription, deadline);
            break;
        case "E":
            String start = parts[3];
            String end = parts[4];
            task = new Event(taskDescription, start, end);
            break;
        default:
            return null;
        }

        if (isDone) {
            task.setDone(true);
        }
        return task;
    }
}
