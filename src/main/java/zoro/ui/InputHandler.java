package zoro.ui;

import java.util.Scanner;

/**
 * Handles user input operations using Scanner.
 * Provides methods to read user input and manage the Scanner resource.
 */
public class InputHandler {

    private final Scanner scanner;

    /**
     * Constructs a new InputHandler with a Scanner for System.in.
     */
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets the next line of user input.
     *
     * @return - the trimmed user input string, or null if no input available
     */
    public String getUserInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        }
        return null;
    }

    /**
     * Closes the Scanner to free up resources.
     */
    public void closeScanner() {
        scanner.close();
    }
}
