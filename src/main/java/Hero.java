import exceptions.HeroException;
import parser.Parser;
import tasks.TaskList;
import commands.CommandHandler;
import messages.MessageHandler;
import database.Database;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class for the Hero application
 * It handles the application startup, user input loop, and command execution
 */
public class Hero {
    private static final String FILE_PATH = "./database/database.txt";
    private static Database database;
    private static TaskList taskList;

    /**
     * The main entry point of the Hero application
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        sendWelcomeMessage();

        try {
            File file = new File(FILE_PATH);

            // Checks for parent directory and creates it if it does not exist
            File parentDir = file.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                throw new IOException("Failed to create parent directory for file: " + FILE_PATH);
            }

            // Checks for file and creates if it does not exist
            if (!file.exists() && !file.createNewFile()) {
                throw new IOException("Failed to create file: " + FILE_PATH);
            }

            database = new Database(FILE_PATH);
            taskList = new TaskList(database.load());

        } catch (IOException e) {
            MessageHandler.sendMessage("An error occurred while trying to open the file.");
            taskList = new TaskList();
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String input = readInput(scanner);
                String[] parsedInput = Parser.parseInput(input);

                if (parsedInput[0].equals("bye")) {
                    break;
                }

                executeCommand(parsedInput);

            } catch (HeroException e) {
                MessageHandler.sendMessage(e.getMessage());
            }

        }
        scanner.close();
        sendExitMessage();
    }

    /**
     * Sends a welcome message to user
     */
    private static void sendWelcomeMessage() {
        MessageHandler.sendMessage("Hello! I'm Hero", "What can I do for you?");
    }

    /**
     * Sends an exit message to user
     */
    private static void sendExitMessage() {
        MessageHandler.sendMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Reads a single line of input from the scanner
     * @param scanner The Scanner object to read from
     * @return The trimmed input string
     */
    private static String readInput(Scanner scanner) {
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine().trim();
    }

    /**
     * Executes the appropriate command based on the parsed input
     * @param parsedInput The array of strings containing the command and its arguments
     */
    private static void executeCommand(String[] parsedInput) {
        try {
            switch (parsedInput[0]) {
            case "list":
                CommandHandler.handleTaskList(taskList);
                break;
            case "mark":
                CommandHandler.handleMark(taskList, parsedInput[1]);
                break;
            case "unmark":
                CommandHandler.handleUnmark(taskList, parsedInput[1]);
                break;
            case "delete":
                CommandHandler.handleDelete(taskList, parsedInput[1]);
                break;
            case "todo":
                CommandHandler.handleToDo(taskList, parsedInput[1]);
                break;
            case "deadline":
                CommandHandler.handleDeadline(taskList, parsedInput[1], parsedInput[2]);
                break;
            case "event":
                CommandHandler.handleEvent(taskList, parsedInput[1], parsedInput[2], parsedInput[3]);
                break;
            case "find":
                CommandHandler.handleFind(taskList, parsedInput[1]);
            }
            database.save(taskList);
        } catch (IOException e) {
            MessageHandler.sendMessage("Error saving tasks: " + e.getMessage());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            MessageHandler.sendMessage("Invalid command format. Please check your task number!");
        }
    }
}