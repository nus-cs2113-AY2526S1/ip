package arpa.home.yikjin.app.kuro.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import arpa.home.yikjin.app.kuro.exception.io.CreateFileIoException;
import arpa.home.yikjin.app.kuro.exception.io.InvalidFileLineException;
import arpa.home.yikjin.app.kuro.exception.io.ReadFileIoException;
import arpa.home.yikjin.app.kuro.exception.io.WriteFileIoException;
import arpa.home.yikjin.app.kuro.parser.FileParser;
import arpa.home.yikjin.app.kuro.task.InvalidTask;
import arpa.home.yikjin.app.kuro.task.Task;
import arpa.home.yikjin.app.kuro.task.TaskManager;
import arpa.home.yikjin.app.kuro.task.TaskType;
import arpa.home.yikjin.app.kuro.ui.Ui;

/**
 * Class to store and load tasks to and from file
 */
public class FileManager {
    private static final Path FILE_PATH = Paths.get("./data/kuro.csv");
    private static final FileParser FILE_PARSER = new FileParser();
    private static final ArrayList<String> FILE_CACHE = new ArrayList<>(5);

    /**
     * Load all tasks from the saved file, if it exists, else create it
     */
    public static void loadFromDisk() {
        if (isFileExists(FILE_PATH)) {
            loadTasksFromExistingFile(FILE_PATH);
        } else {
            createFile(FILE_PATH);
        }
    }

    /**
     * Check if the given file exists
     *
     * @param filePath File to check if it exists
     *
     * @return If the given file exists
     */
    private static boolean isFileExists(final Path filePath) {
        return Files.isRegularFile(filePath);
    }

    /**
     * Load all tasks from the existing file
     *
     * @param filePath Path of existing file to load tasks from
     */
    private static void loadTasksFromExistingFile(final Path filePath) {
        if (!Files.isReadable(filePath)) {
            throw new ReadFileIoException("access denied!");
        }

        try (final BufferedReader reader = Files.newBufferedReader(filePath)) {
            reader.lines().forEachOrdered((line) -> {
                FILE_PARSER.reset();

                try {
                    FILE_PARSER.parseLineAsTask(line);
                    TaskManager.addTask(FILE_PARSER.getParsedTask(), false);
                } catch (final InvalidFileLineException e) {
                    Ui.errException(e);
                    TaskManager.addTask(new InvalidTask(), false);
                }

                FILE_CACHE.add(line);
            });
        } catch (final IOException e) {
            throw new ReadFileIoException(e.getLocalizedMessage());
        }
    }

    /**
     * Create the new save file at the given path
     *
     * @param filePath Path to save new task file at
     */
    private static void createFile(final Path filePath) {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (final IOException e) {
            throw new CreateFileIoException(e.getLocalizedMessage());
        }
    }

    /**
     * Save a single task to the end of the file
     *
     * @param task Task to append to save file
     */
    public static void saveTask(final Task task) {
        final String taskLine = FileParser.parseTaskAsLine(task);

        FILE_CACHE.add(taskLine);

        try (final BufferedWriter writer = Files.newBufferedWriter(FILE_PATH, StandardOpenOption.WRITE,
                StandardOpenOption.APPEND)) {
            writer.append(taskLine);
            writer.newLine();
            writer.flush();
        } catch (final IOException e) {
            throw new WriteFileIoException(e.getLocalizedMessage());
        }
    }

    /**
     * Delete the task at the given index from the save file
     *
     * @param taskIndex Index of task to delete
     */
    public static void deleteTask(final int taskIndex) {
        FILE_CACHE.remove(taskIndex);
        saveAllTasks();
    }

    /**
     * Save all tasks from the cache to the save file
     */
    private static void saveAllTasks() {
        try (final BufferedWriter writer = Files.newBufferedWriter(FILE_PATH, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            for (final String line : FILE_CACHE) {
                writer.write(line);
                writer.newLine();
            }

            writer.flush();
        } catch (final IOException e) {
            throw new WriteFileIoException(e.getLocalizedMessage());
        }
    }

    /**
     * Update the task at a given index in the save file
     *
     * @param taskIndex Index of task to update
     * @param task      Updated task to replace the task at the given index
     */
    public static void updateTask(final int taskIndex, final Task task) {
        if (task.getTaskType() == TaskType.INVALID) {
            return;
        }

        FILE_CACHE.set(taskIndex, FileParser.parseTaskAsLine(task));
        saveAllTasks();
    }
}
