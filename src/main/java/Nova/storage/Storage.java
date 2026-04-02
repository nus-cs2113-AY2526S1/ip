package Nova.storage;

import Nova.exception.NovaException;
import Nova.task.Task;
import Nova.task.Deadline;
import Nova.task.TaskList;
import Nova.task.Todo;
import Nova.task.Event;


import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;


/**
 * The {@code Storage} class handles reading and writing tasks to a file.
 * It manages loading tasks from the file into memory and saving task changes
 * back to the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new {@code Storage} object with the given file path.
     *
     * @param filePath the file path where tasks are saved
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into an ArrayList.
     * <p>
     * If the file or its parent directory does not exist, they will be created.
     * Supports Todo, Deadline, and Event task types.
     *
     * @return an ArrayList of tasks loaded from the file
     * @throws NovaException If there is an error reading the file or unknown task type
     */
    public ArrayList<Task> loadTasks() throws NovaException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    System.out.println(" OOPS!!! Failed to create directory " + parentDir.getAbsolutePath());
                }
            }

            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.out.println(" OOPS!!! Failed to create file " + file.getAbsolutePath());
                }
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length < 3) {
                    continue;
                }

                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }

                String type = parts[0].trim();
                boolean isDone = parts[1].equals("1");
                String description = parts[2].trim();

                Task task;
                switch (type.trim()) {
                case "T" -> task = new Todo(description);
                case "D" -> task = new Deadline(description, parts[3]);
                case "E" -> {
                    String[] timeParts = parts[3].split(" to ",2);
                    task = new Event(description, timeParts[0], timeParts[1]);
                }
                default -> throw new IOException("Unknown task type: " + type);
                }
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            sc.close();
        } catch (IOException e) {
            throw new NovaException("Error loading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves all tasks in the given {@link TaskList} to the file.
     * <p>
     * Each task is written in a consistent format:
     * Todo: T | status | description
     * Deadline: D | status | description | by
     * Event: E | status | description | from to to
     *
     * @param tasks the TaskList containing tasks to save
     */
    public void saveTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task task : tasks.getAllTasks()) {
                String line = "";

                if (task instanceof Todo) {
                    line = "T | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription();
                } else if (task instanceof Deadline) {
                    line = "D | " + (task.getIsDone() ? "1" : "0")
                            + " | " + task.getDescription()
                            + " | " + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    line = "E | " + (task.getIsDone() ? "1" : "0")
                            + " | " + task.getDescription() + " | "
                            + ((Event) task).getFrom() + " to "
                            + ((Event) task).getTo();
                }
                fw.write(line + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(" OOPS!!! Error saving file: " + e.getMessage());
        }
    }
}
