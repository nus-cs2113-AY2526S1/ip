package darren;

import darren.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        
        FileWriter fw = new FileWriter(file);
        for (Task task : tasks) {
            fw.write(task.toStorage() + System.lineSeparator());
        }
        fw.close();
    }

    public ArrayList<Task> loadTasks() throws DarrenException {
        File f = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        
        if (!f.exists()) {
            return tasks;
        }

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                try {
                    Task task = Task.fromStorage(s.nextLine());
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    //skip corrupted lines
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            //file doesn't exist, return empty list
        }
        return tasks;
    }


}