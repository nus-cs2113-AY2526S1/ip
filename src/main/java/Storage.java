import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks from/to a file.
 */
public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws FileNotFoundException if the file does not exist.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File newFile = new File(filepath);
        if (!newFile.exists()) {
            return tasks;
        }

        Scanner scanner = new Scanner(newFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) continue;
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task newTask;
            switch (type) {
            case "T":
                newTask = new ToDo(description);
                break;
            case "D":
                if (parts.length < 4) continue;
                newTask = new Deadline(description, parts[3]);
                break;
            case "E":
                if (parts.length < 4) continue;
                String[] times = parts[3].split(" to ");
                if (times.length < 2) continue;
                newTask = new Event(description, times[0], times[1]);
                break;
            default:
                continue;
            }
            newTask.isDone = isDone;
            tasks.add(newTask);
        }
        scanner.close();
        return tasks;
    }

    /**
     * Saves the given tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException if an I/O error occurs while saving.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {

        File saveFilePath = new File(filepath);
        // Create file if not exist
        saveFilePath.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(saveFilePath);
        for (Task currentTask: tasks) {
            String line;
            String status = currentTask.isDone ? "1" : "0";
            if (currentTask instanceof ToDo) {
                line = "T | " + status + " | " + currentTask.description;
            } else if (currentTask instanceof Deadline) {
                line = "D | " + status + " | " + currentTask.description + " | " + ((Deadline) currentTask).do_by; // Cast as a Deadline class
            } else if (currentTask instanceof Event) {
                line = "E | " + status + " | " + currentTask.description + " | " + ((Event) currentTask).start_dateTime + " to " + ((Event) currentTask).end_dateTime; // Cast as an Event class
            } else {
                continue;
            }
            writer.write(line + System.lineSeparator());

        }
        writer.close();
    }
}
