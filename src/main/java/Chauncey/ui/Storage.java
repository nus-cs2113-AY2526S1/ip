package Chauncey.ui;

import Chauncey.exception.ChaunceyException;
import Chauncey.task.Deadline;
import Chauncey.task.Event;
import Chauncey.task.Task;
import Chauncey.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void addTaskFromFile(String line, ArrayList<Task> tasks) throws ChaunceyException{
        String[] taskDetails = line.split("\\|");
        if (taskDetails.length < 3) {
            throw new ChaunceyException("Corrupted data: insufficient fields in line (at least 3 fields is needed: " + line);
        }
        switch (taskDetails[0].trim()) {
        case "T":
            if (taskDetails.length != 3) {
                throw new ChaunceyException("Corrupted data: Exactly 3 fields is needed for todo.");
            }
            tasks.add(new Todo(taskDetails[2].trim()));
            updateTaskStatus(taskDetails[1].trim(), tasks.get(tasks.size() - 1));
            break;
        case "D":
            if (taskDetails.length != 4) {
                throw new ChaunceyException("Corrupted data: Exactly 4 fields is needed for deadline.");
            }
            tasks.add(new Deadline(taskDetails[2].trim(), taskDetails[3].trim()));
            updateTaskStatus(taskDetails[1].trim(), tasks.get(tasks.size() - 1));
            break;
        case "E":
            if (taskDetails.length != 5) {
                throw new ChaunceyException("Corrupted data: Exactly 5 fields is needed for event.");
            }
            tasks.add(new Event(taskDetails[2].trim(), taskDetails[3].trim(), taskDetails[4].trim()));
            updateTaskStatus(taskDetails[1].trim(), tasks.get(tasks.size() - 1));
            break;
        default:
            throw new ChaunceyException("Task type is invalid.");
        }
    }

    private void updateTaskStatus(String taskStatus, Task task) {
        if (taskStatus.equals("1")) {
            task.markAsDone();
        }
    }

    public void saveToFile(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
        FileWriter fw = new FileWriter(filePath);
        for (Task task: tasks) {
            fw.write(task.writeToFile() + "\n");
        }
        fw.close();
    }

    public ArrayList<Task> loadFile() throws FileNotFoundException, ChaunceyException {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner fileReader = new Scanner(file);
        String line;
        while (fileReader.hasNext()) {
            line = fileReader.nextLine();
            addTaskFromFile(line, tasks);
        }
        return tasks;
    }
}
