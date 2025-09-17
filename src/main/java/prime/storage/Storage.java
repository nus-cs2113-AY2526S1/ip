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

public class Storage {
    private static final String FILE_PATH = "./data/duke.txt";
    UserInterface ui = new UserInterface();

    // Load tasks from file
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            // Create folder and file if missing
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

    // Save tasks to file
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

    // Convert a line from file into a Task object
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

    // Convert a Task object into a string to save in file
    private String formatTask(Task task) {
        String status;
        if (task.getIsDone()){
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
