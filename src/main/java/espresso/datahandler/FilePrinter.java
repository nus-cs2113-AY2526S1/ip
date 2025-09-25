package espresso.datahandler;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for writing task data to a file.
 * Supports appending, overwriting, and fallback recovery on write failure.
 */
public class FilePrinter {

    /**
     * Writes content to the specified file.
     *
     * @param filePath the path to the file
     * @param content  the content to write
     * @param append   whether to append to the file or overwrite it
     * @return {@code true} if the write succeeds
     * @throws IOException if the file cannot be accessed or written
     */
    public static boolean writeToFile(String filePath, String content, boolean append) throws IOException {
        FileWriter fw = new FileWriter(filePath, append);
        fw.write(content);
        fw.close();
        return true;
    }

    /**
     * Attempts to write content to the file, returning {@code false} on failure.
     * This method wraps {@link #writeToFile} and suppresses IO exceptions.
     *
     * @param filePath the path to the file
     * @param content  the content to write
     * @param append   whether to append to the file or overwrite it
     * @return {@code true} if the write succeeds; {@code false} otherwise
     */
    public static boolean writeToFileAgain(String filePath, String content, boolean append) {
        try {
            return writeToFile(filePath, content, append);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Writes a line of content to the file, with fallback recovery if the initial write fails.
     * If an {@code IOException} occurs, attempts to create the file and retry.
     *
     * @param filePath the path to the file
     * @param content  the content to write
     * @param append   whether to append to the file or overwrite it
     * @return {@code true} if the write succeeds; {@code false} otherwise
     */
    public static boolean writeLine(String filePath, String content, boolean append) {
        try {
            return writeToFile(filePath, content, append);
        } catch (IOException e) {
            FileHandler.createFile();
            return writeToFileAgain(filePath, content, append);
        }
    }

    /**
     * Overwrites the file with the given content.
     * Internally calls {@link #writeLine} with {@code append = false}.
     *
     * @param filePath the path to the file
     * @param lines    the content to write, replacing all existing data
     * @return {@code true} if the write succeeds; {@code false} otherwise
     */
    public static boolean rewriteLines(String filePath, String lines) {
        return writeLine(filePath, lines, false);
    }
}
