import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages all interactions with the stored data to save the ArrayList between separate sessions
 */
public class FileHandler {
    private static final String FILENAME = "storedData.txt";

    public FileHandler(TaskList taskList) {
        System.out.println("Loading stored data...");
        readFile(taskList);
    }

    /**
     * Reads storedData file on startup and populates ArrayList with saved tasks
     *
     * @param taskList the ArrayList of Task classes
     */
    private static void readFile(TaskList taskList) {
        File file = new File(FILENAME);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + FILENAME);
                }
            } catch (IOException e) {
                System.out.println("Error opening file: " + FILENAME);
                e.printStackTrace();
            }
        }

        try (Scanner scanner = new Scanner(file)) {
            //file exists
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                taskList.loadTask(line);

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + FILENAME);
        }
    }

    /**
     * Appends a Task to the end of the storedData file
     *
     * @param task a Task object
     * @param append boolean to determine if Task class is appended to end of file
     */
    public static void addTask(Task task, boolean append) {
        try (FileWriter fw = new FileWriter(FILENAME, append)) {
            if (task instanceof Event) {
                String newTask = "E||" + task.isMarked() + "||" + task.getTaskDescription() + "||" + ((Event) task).getStartDate() + "||" + ((Event) task).getEndDate();
                fw.write(newTask + '\n');
            } else if (task instanceof Deadline) {
                String newTask = "D||" + task.isMarked() + "||" + task.getTaskDescription() + "||" + ((Deadline) task).getDeadline();
                fw.write(newTask + '\n');
            } else if (task instanceof Todo) {
                String newTask = "T||" + task.isMarked() + "||" + task.getTaskDescription();
                fw.write(newTask + '\n');
            }
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    /**
     * Rewrites storedData file with the current ArrayList of Tasks classes
     *
     * @param taskList the ArrayList of Task classes
     */
    public static void updateFile(ArrayList<Task> taskList) {
        int init = 0;
        for (Task task : taskList) {
            if (init == 0) {
                addTask(task, false);
                init = 1;
            } else {
                addTask(task, true);
            }
        }
    }
}
