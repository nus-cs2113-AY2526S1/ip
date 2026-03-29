package michael.Storage;

import michael.Parser.ParseInput;
import michael.TaskList.Deadline;
import michael.TaskList.Event;
import michael.TaskList.Task;
import michael.TaskList.Todo;
import michael.Ui.UserMessages;
import michael.Command.AddCommand;
import michael.Command.Command;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static michael.Michael.*;

/**
 * This class manages storage operations for tasks.
 * It provides utility methods to create and update the data file
 * where tasks are stored, append new task data, update or delete existing entries,
 * and read tasks from the file to reconstruct the current task list.
 */
public class Storage {


    /**
     * Appends a given text to the end of the specified file, and throws an error if its is not able to append to file.
     *
     * @param filePath     Path to the file to append to
     * @param textToAppend The textual data to add
     */
    public static void appendToFile(String filePath, String textToAppend) {
        FileWriter fw;
        try {
            fw = new FileWriter(filePath, true);
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            System.out.println("Oh no, I can't add the task to your file :{. " +
                    "Please make sure the file follows this path, '/home/ip/data'");
            throw new RuntimeException(e);
        }

    }

    /**
     * Updates data at a specified index in the file, replacing old data with new data.
     *
     * @param index   Line number to update
     * @param oldData The original text within the line to replace
     *                (i.e. whether the task is marked / unmarked)
     * @param newData The new text to be inserted in place of oldData
     *                (i.e. whether the user wishes to mark / unmark task)
     */
    public void writeToPosition(int index, String oldData, String newData) {
        Path filePath = Paths.get(dataFile);
        try {
            List<String> lines = Files.readAllLines(filePath);
            String line = lines.get(index);
            String updatedLine = line.replace(oldData, newData);
            lines.set(index, updatedLine);
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Oh no! I was not able to update the file: " + e.getMessage());
        }
    }

    /**
     * Creates a new file at the given path
     * Creates any necessary parent directories if it does not exist
     *
     * @param filePath Path to the file to create
     */
    public void createFile(String filePath) {
        File f = new File(filePath);
        File parentDir = f.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            if (parentDir.mkdirs()) {
                System.out.println("Parent directory created successfully.");
            } else {
                System.out.println("Failed to create parent directory.");
                return;
            }
        }

        try {
            if (f.createNewFile()) {
                System.out.println("Yay! A file created successfully.");
            }
        } catch (IOException e) {
            System.out.println(" :{ an error occurred while creating the file: " + e.getMessage());
        }

    }

    /**
     * Re-formats the file data for tasks after a deletion, refreshing line indices so
     * task numbers are sequential. Called mainly after a line is removed.
     *
     * @param lines List of lines representing tasks in the file
     */
    public void formatFileAfterDelete(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int dotPos = line.indexOf('.');
            if (dotPos != -1) {
                String restOfLine = line.substring(dotPos + 1).trim();
                lines.set(i, i + 1 + ". " + restOfLine);
            }
        }
    }

    /**
     * Removes a task at the specified index from the file, then updates all
     * remaining entries so their indices are consistent.
     *
     * @param index Line number of the task to delete
     */
    public void deleteTask(int index) {
        Path filePath = Paths.get(dataFile);
        try {
            List<String> lines = Files.readAllLines(filePath);
            if (index >= 0 && index < lines.size()) {
                lines.remove(index);
                formatFileAfterDelete(lines);
                Files.write(filePath, lines);
                System.out.println("Your task has been deleted successfully.");
            } else {
                System.out.println("Index out of bounds.");
            }
        } catch (IOException e) {
            System.out.println("Oh no! An error occurred: " + e.getMessage());
        }
    }

    /**
     * Constructor for the Storage class.
     * Initializes the file name and ensures the file exists.
     *
     * @param dataFile Path of the data file for task storage
     */
    public Storage(String dataFile) {
        createFile(dataFile);
    }

    /**
     * Reads all tasks from the data file and reconstructs them as Task objects.
     * Uses specific parsers and commands for each type (Todo, Deadline, Event) and
     * executes commands to add them to the active task list.
     */
    public void getFileData() {
        File f = new File(dataFile);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean isTaskDone;
        while (s.hasNext()) {
            String line = s.nextLine();
            int dotPos = line.indexOf('.');
            String restOfLine = line.substring(dotPos + 1);
            isTaskDone = restOfLine.charAt(5) == '1';
            if (restOfLine.charAt(1) == 'T') {
                loadTodo(restOfLine, isTaskDone);
            } else if (restOfLine.charAt(1) == 'D') {
                loadDeadline(restOfLine, isTaskDone);
            } else {
                loadEvent(restOfLine, isTaskDone);
            }
        }
    }

    /**
     * Loads a Todo task from the file data and adds it to the task list.
     *
     * @param restOfLine Remaining line content after the task index
     * @param isTaskDone Boolean indicating if the task is marked as done
     */
    public void loadTodo(String restOfLine, boolean isTaskDone) {
        Task task = new Todo(restOfLine.substring(9), dataFile, numberTasks + 1, isTaskDone, false);
        Command addCommand = new AddCommand(task, false);
        addCommand.executeCommand(tasks, new UserMessages(), new Storage(dataFile));
    }

    /**
     * Loads a Deadline task from the file data and adds it to the task list.
     *
     * @param restOfLine Remaining line content after the task index
     * @param isTaskDone Boolean indicating if the task is marked as done
     */
    public void loadDeadline(String restOfLine, boolean isTaskDone) {
        String taskSub = restOfLine.substring(9);
        String newTaskSub = "deadline " + taskSub.replace("|", "/by");
        ParseInput deadlinerParser = new ParseInput(newTaskSub);
        String[] deadlineInstruction = deadlinerParser.parseDeadline();

        Task task = new Deadline(deadlineInstruction[0], deadlineInstruction[1], dataFile, numberTasks + 1, isTaskDone, false);
        Command addCommand = new AddCommand(task, false);
        addCommand.executeCommand(tasks, new UserMessages(), new Storage(dataFile));
    }

    /**
     * Loads an Event task from the file data and adds it to the task list.
     *
     * @param restOfLine Remaining line content after the task index
     * @param isTaskDone Boolean indicating if the task is marked as done
     */
    public void loadEvent(String restOfLine, boolean isTaskDone) {
        String taskSub = restOfLine.substring(9);
        String newTaskSub = taskSub.replace("|", "/from");
        newTaskSub = "event " + newTaskSub.replace("-", " /to ");
        ParseInput eventParser = new ParseInput(newTaskSub);
        String[] eventInstruction = eventParser.parseEvent();

        Task task = new Event(eventInstruction[0], eventInstruction[1], eventInstruction[2], dataFile, numberTasks + 1, isTaskDone, false);
        Command addCommand = new AddCommand(task, false);
        addCommand.executeCommand(tasks, new UserMessages(), new Storage(dataFile));
    }
}
