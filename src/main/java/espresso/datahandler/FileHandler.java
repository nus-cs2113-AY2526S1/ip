package espresso.datahandler;
import java.io.File;
import java.io.IOException;

import espresso.tasks.Task;
import espresso.ui.Display;

/**
 * Handles file operations such as creating, reading, writing, and updating
 * the task data file used by the application.
 */
public class FileHandler {
    private static final String FILE_PATH = "espresso.txt";
    private static final String ERROR_CREATE_NEW_FILE_MESSAGE = "An error occurred in creating/finding the data file.";

    private static File file = new File(FILE_PATH);
    private static String[] fileContents;

    /**
     * Creates the data file if it does not already exist.
     * Displays an error message if the file cannot be created.
     */
    public static void createFile() {
        try {
            if (!file.createNewFile()) {
                Display.printMessage(ERROR_CREATE_NEW_FILE_MESSAGE);
            };
        } catch (IOException e) {
            Display.printMessage(ERROR_CREATE_NEW_FILE_MESSAGE);
        }
    }

    /**
     * Writes a single task line to the data file.
     * @param taskLine the string representation of the task
     * @param append   whether to append to the file or overwrite
     */
    public static void writeTask(String taskLine, boolean append) {
        boolean success = FilePrinter.writeLine(FILE_PATH, taskLine + "\n", append);
        if (!success) {
            Display.printMessage(ERROR_CREATE_NEW_FILE_MESSAGE);
        }
    }


    /**
     * Rewrites the entire file with the given content.
     *
     * @param content the new content to write to the file
     */
    public static void rewriteFile(String content) {
        boolean success = FilePrinter.rewriteLines(FILE_PATH, content);
        if (!success) {
            Display.printMessage(ERROR_CREATE_NEW_FILE_MESSAGE);
        }
    }

    /**
     * Loads task data from the file and parses it into {@code Task[]} objects.
     *
     * @return an array of parsed {@code Task} objects
     */
    public static Task[] loadData() {
        fileContents = FileReader.loadFile(file);
        return FileParser.parseTasks(fileContents);
    }

    /**
     * Prints the raw contents of the data file to the display.
     * Only prints if the file contents have been loaded.
     */
    public static void printRawData() {
        if (fileContents != null) {
            Display.printMessage(fileContents);
        }
    }
}

