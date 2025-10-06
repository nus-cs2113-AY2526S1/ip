package paddington;

import paddington.parser.Parser;
import paddington.storage.Storage;
import paddington.task.TaskList;
import paddington.ui.PaddingtonException;
import paddington.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class for the Paddington chatbot.
 * Initialises storage, loads tasks and processes user input until bye command is inputted.
 */
public class Paddington {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Main entry point of the Paddington chatbot.
     * Initializes storage, loads tasks, displays a welcome message, and processes user input.
     * Saves tasks if modified.
     *
     * @param args command-line arguments (unused)
     * @throws PaddingtonException if there's an error loading storage or tasks
     */
    public static void main(String[] args) throws PaddingtonException {
        // Load data from storage
        try {
            Storage.init();
            Storage.load();
        } catch (IOException e) {
            throw new PaddingtonException(e.getMessage());
        }

        // Start
        Ui.printWelcomeMessage();

        while (!Parser.getIsQuit()) {
            String userInput = scanner.nextLine();
            Ui.printHorizontalLine();

            try {
                Parser.parseInput(userInput);
            } catch (PaddingtonException e) {
                Ui.printErrorDescription(e.getMessage());
            }

            Ui.printHorizontalLine();

            if (Parser.getIsTaskListChanged()) {
                Storage.save(TaskList.getTaskList());
            }
        }

        scanner.close();
    }
}
