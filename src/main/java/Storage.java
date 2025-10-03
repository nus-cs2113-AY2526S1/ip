import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public record Storage(String FILE_PATH) {

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                File parentDir = file.getParentFile();
                if (!parentDir.exists() && !parentDir.mkdirs()) {
                    System.out.println("Warning: could not create directory " + parentDir.getAbsolutePath());
                }
                if (!file.createNewFile()) {
                    System.out.println("Warning: could not create file " + file.getAbsolutePath());
                }
                return tasks;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];

                switch (type) {
                case "T": {
                    Todo t = new Todo(desc);
                    if (isDone) t.markAsDone();
                    tasks.add(t);
                    break;
                }
                case "D": {
                    Deadline d = new Deadline(desc, parts[3]);
                    if (isDone) d.markAsDone();
                    tasks.add(d);
                    break;
                }
                case "E": {
                    Event e = new Event(desc, parts[3], parts[4]);
                    if (isDone) e.markAsDone();
                    tasks.add(e);
                    break;
                }
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                System.out.println("Warning: could not create directory " + parentDir.getAbsolutePath());
            }

            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    fw.write("T | " + (task.isDone ? "1" : "0")
                            + " | " + task.description + System.lineSeparator());
                } else if (task instanceof Deadline d) {
                    fw.write("D | " + (d.isDone ? "1" : "0")
                            + " | " + d.description + " | " + d.by + System.lineSeparator());
                } else if (task instanceof Event e) {
                    fw.write("E | " + (e.isDone ? "1" : "0")
                            + " | " + e.description + " | " + e.from + " | " + e.to + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}