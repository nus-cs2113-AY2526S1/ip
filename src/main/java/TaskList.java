import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Maintains an ArrayList of Task classes.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a Task class to the ArrayList and adds the task to the stored file.
     *
     * @param task a Task class
     * @param fileHandler the FileHandler class used to update the stored data
     */
    public void addTask(Task task, FileHandler fileHandler) {
        tasks.add(task);
        System.out.println("added: " + task.getTaskDescription());
        FileHandler.addTask(task, true);
    }

    /**
     * Remove Task class based on its index and removes it from the stored file.
     *
     * @param index index of Task class to be removed
     * @param fileHandler the FileHandler class used to update the stored data
     */
    public void removeTask(int index, FileHandler fileHandler) {
        System.out.println("removed: " + tasks.get(index).getTaskDescription());
        tasks.remove(index);
        FileHandler.updateFile(tasks);
    }

    /**
     * Creates a Task object in the ArrayList from String extracted from the stored data
     *
     * @param line a string that contains information of a Task class that was stored
     */
    public void loadTask(String line) {
        String[] lineArray = line.split("\\|\\|");
        if (lineArray[0].equals("T")) {
            tasks.add(new Todo(lineArray[2]));
            System.out.println("added: " + lineArray[2]);
            if (lineArray[1].equals("true")) {
                markTaskAsDone(tasks.size() - 1);
            }
        } else if (lineArray[0].equals("D")) {
            try {
                LocalDate deadline = LocalDate.parse(lineArray[3]);
                tasks.add(new Deadline(lineArray[2], deadline));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid deadline format, please enter in a yyyy-mm-dd format");
            }
            System.out.println("added: " + lineArray[2]);
            if (lineArray[1].equals("true")) {
                markTaskAsDone(tasks.size() - 1);
            }
        } else if (lineArray[0].equals("E")) {
            tasks.add(new Event(lineArray[2], lineArray[3], lineArray[4]));
            System.out.println("added: " + lineArray[2]);
            if (lineArray[1].equals("true")) {
                markTaskAsDone(tasks.size() - 1);
            }
        } else {
            System.out.println("Invalid line format");
        }
    }

    /**
     * Prints all tasks in ArrayList
     */
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.print((i + 1) + ".");
            tasks.get(i).printTask();
        }
    }

    /**
     * Searches through ArrayList for task descriptions that contain a certain string
     *
     * @param string a string to search for within the task descriptions
     */
    public void findTask(String string) {
        int j = 1;
        for (Task task : tasks) {
            if (task.getTaskDescription().contains(string)) {
                System.out.print(j + ".");
                task.printTask();
                j++;
            }
        }
    }

    /**
     * Marks a Task as done based on the index
     *
     * @param id index of task to be marked
     */
    public void markTaskAsDone(int id) {
        tasks.get(id).setIsMarked(true);
        System.out.println("marked task " + tasks.get(id).getTaskDescription() + " as done");
        FileHandler.updateFile(tasks);
    }

    /**
     * Marks a Task as undone based on the index
     *
     * @param id index of task to be unmarked
     */
    public void markTaskAsNotDone(int id) {
        tasks.get(id).setIsMarked(false);
        System.out.println("marked task " + tasks.get(id).getTaskDescription() + " as not done");
        FileHandler.updateFile(tasks);
    }
}
