package prime.storage;

import prime.task.Deadline;
import prime.task.Event;
import prime.task.Task;
import prime.task.ToDo;
import prime.ui.UserInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles persistent storage of tasks for the Prime Task Management system.
 * <p>
 * The {@code Storage} class is responsible for saving tasks to a file and
 * loading tasks back into memory. Tasks are stored in a plain text file
 * with a consistent format.
 * </p>
 */
public class Storage {
    /**
     * The path to the file where tasks are stored.
     */
    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * The UI instance used for displaying messages to the user.
     */
    UserInterface ui = new UserInterface();

    /**
     * Loads tasks from the storage file.
     * <p>
     * If the file or its parent directory does not exist, they will be created.
     * </p>
     *
     * @return A list of {@code Task} objects loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            if (!file.exists()) {
                boolean dirSuccessful = file.getParentFile().mkdirs();
                boolean fileSuccessful = file.createNewFile();
                if (dirSuccessful && fileSuccessful) {
                    ui.printIndented("Files created successfully");
                }
                return tasks;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            br.close();
        } catch (IOException e) {
            ui.printIndented("Error reading file");
        }

        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks The list of {@code Task} objects to save.
     */
    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                bw.write(formatTask(task));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Converts a line from the storage file into a {@code Task} object.
     * <p>
     * Supports parsing of ToDo, Deadline, and Event task formats.
     * Returns {@code null} if the line is malformed or cannot be parsed.
     * </p>
     *
     * @param line The line of text to parse.
     * @return A {@code Task} object, or {@code null} if parsing fails.
     */
    private Task parseTask(String line) {
        String[] parts = line.split("\\|");
        try {
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String desc = parts[2].trim();

            switch (type) {
            case "T":
                ToDo t = new ToDo(desc);
                t.setIsDone(isDone);
                return t;
            case "D":
                Deadline d = new Deadline(desc, parts[3].trim());
                d.setIsDone(isDone);
                return d;
            case "E":
                String[] times = parts[3].trim().split("-");
                String from = times[0].trim();
                String to = times.length > 1 ? times[1].trim() : "";
                Event e = new Event(desc, from, to);
                e.setIsDone(isDone);
                return e;
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Converts a {@code Task} object into a formatted string
     * suitable for saving to the storage file.
     *
     * @param task The {@code Task} to format.
     * @return A string representation of the task for file storage.
     */
    private String formatTask(Task task) {
        String status;
        if (task.getIsDone()) {
            status = "1";
        } else {
            status = "0";
        }

        if (task instanceof ToDo) {
            return "T | " + status + " | " + task.getDescription();
        } else if (task instanceof Deadline deadline) {
            return "D | " + status + " | " + deadline.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Event event) {
            return "E | " + status + " | " + event.getDescription() + " | " + event.getFrom() + "-" + event.getTo();
        }
        return "";
    }
}
