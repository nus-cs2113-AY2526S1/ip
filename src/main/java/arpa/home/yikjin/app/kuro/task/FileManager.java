package arpa.home.yikjin.app.kuro.task;

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
import arpa.home.yikjin.app.kuro.ui.Ui;

public class FileManager {
    private static final Path FILE_PATH = Paths.get("./data/kuro.csv");
    private static final FileParser FILE_PARSER = new FileParser();
    private static final ArrayList<String> FILE_CACHE = new ArrayList<>(5);

    public static void loadFromDisk() {
        if (isFileExists(FILE_PATH)) {
            loadTasksFromExistingFile(FILE_PATH);
        } else {
            createFile(FILE_PATH);
        }
    }

    private static boolean isFileExists(final Path filePath) {
        return Files.isRegularFile(filePath);
    }

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

    private static void createFile(final Path filePath) {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (final IOException e) {
            throw new CreateFileIoException(e.getLocalizedMessage());
        }
    }

    static void saveTask(final Task task) {
        final String taskLine = FileParser.parseTaskAsLine(task);

        FILE_CACHE.add(taskLine);

        try (final BufferedWriter writer = Files.newBufferedWriter(FILE_PATH,
                StandardOpenOption.WRITE, StandardOpenOption.APPEND)) {
            writer.append(taskLine);
            writer.newLine();
            writer.flush();
        } catch (final IOException e) {
            throw new WriteFileIoException(e.getLocalizedMessage());
        }
    }

    static void deleteTask(final int taskIndex) {
        FILE_CACHE.remove(taskIndex);
        saveAllTasks();
    }

    private static void saveAllTasks() {
        try (final BufferedWriter writer = Files.newBufferedWriter(FILE_PATH,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (final String line : FILE_CACHE) {
                writer.write(line);
                writer.newLine();
            }

            writer.flush();
        } catch (final IOException e) {
            throw new WriteFileIoException(e.getLocalizedMessage());
        }
    }

    static void updateTask(final int taskIndex, final Task task) {
        if (task.getTaskType() == TaskType.INVALID) {
            return;
        }

        FILE_CACHE.set(taskIndex, FileParser.parseTaskAsLine(task));
        saveAllTasks();
    }
}
