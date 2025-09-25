package espresso.datahandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import espresso.ui.Display;

/**
 * Utility class for reading task data from a file.
 * Handles file loading, line filtering, and fallback recovery if the file is missing.
 */
public class FileReader {
    private static final String UNABLE_TO_CREATE_FILE_ERROR = "The system is currently unable to create a file.";

    /**
     * Reads non-empty lines from the given file.
     *
     * @param file the file to read from
     * @return array of non-empty lines from the file
     * @throws FileNotFoundException if the file cannot be found
     */
    private static String[] getFileContents(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            if (!line.isEmpty()) {
                list.add(line);
            }
        }
        return list.toArray(new String[0]);
    }

    /**
     * Attempts to read the file again, displaying an error message if it fails.
     *
     * @param file the file to read from
     * @return array of lines from the file, or {@code null} if reading fails
     */
    private static String[] loadFileAgain(File file) {
        try {
            return getFileContents(file);
        } catch (FileNotFoundException ex) {
            Display.printMessage(UNABLE_TO_CREATE_FILE_ERROR);
            return null;
        }
    }

    /**
     * Loads the contents of the file, creating it if it does not exist.
     * Filters out empty lines and returns the result as a string array.
     *
     * @param file the file to load
     * @return array of non-empty lines from the file, or {@code null} if loading fails
     */
    public static String[] loadFile(File file) {
        try {
            return getFileContents(file);
        } catch (FileNotFoundException e) { // if the file is not created, create the file
            FileHandler.createFile();
            return loadFileAgain(file);
        }
    }
}
