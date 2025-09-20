package superidol.storage;

import superidol.task.Deadline;
import superidol.task.Event;
import superidol.task.Task;
import superidol.task.Todo;
import superidol.tasklist.TaskList;
import superidol.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File file;
    private TaskList taskList;

    private String dataSeparator = "\\|";

    public Storage(String path, TaskList taskList) {
        this.taskList = taskList;

        File f = new File(path);
        if (!f.exists()) {
            try {
                if (!f.createNewFile()) {
                    Ui.respondCannotCreateNewFile();
                }
            } catch (IOException e) {
                Ui.respond(e.getMessage());
            }
        }

        this.file = f;
    }

    public File getFile() {
        return file;
    }

    public void loadSavedFile() {
        if (!file.exists()) {
            return;
        }

        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                loadTask(s.nextLine());
            }
            Ui.respondLoadedLocalSave();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTask(String data) {
        String[] parts = data.split(dataSeparator);
        // mark or unmark
        boolean isDone;
        if (parts[1].equals("0")) {
            isDone = false;
        } else if (parts[1].equals("1")) {
            isDone = true;
        } else {
            Ui.respondInvalidData(data);
            return;
        }
        switch (parts[0]) {
        case "T":
            if (parts.length == 3) {
                Todo todo = new Todo(parts[2], isDone);
                taskList.addTask(todo, false);
            } else {
                Ui.respondInvalidData(data);
            }
            break;
        case "D":
            if (parts.length == 4) {
                Deadline deadline = new Deadline(parts[2], parts[3], isDone);
                taskList.addTask(deadline, false);
            } else {
                Ui.respondInvalidData(data);
            }
            break;
        case "E":
            if (parts.length == 5) {
                Event event = new Event(parts[2], parts[3], parts[4], isDone);
                taskList.addTask(event, false);
            } else {
                Ui.respondInvalidData(data);
            }
            break;
        default:
            Ui.respondInvalidData(data);
        }
    }

    public void saveToFile(){
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : taskList.getTaskList()) {
                fw.write(task.saveData() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            Ui.respondCannotOpenFile();
        }
    }
}
