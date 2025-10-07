package prime;

import prime.command.Command;
import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.parser.Parser;
import prime.ui.UserInterface;

import java.util.Scanner;

/**
 * The entry point of the Prime Task Management application.
 * <p>
 * This class initializes the necessary components (UI, task manager, parser),
 * handles user input, executes commands, and manages the application loop
 * until the user issues an exit command.
 * </p>
 */
public class Prime {

    /**
     * The main method that starts the Prime application.
     * <p>
     * It initializes the user interface, task manager, and input scanner.
     * The application runs in a loop until the user chooses to exit.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String userInput = scanner.nextLine().trim();
            try {
                Command command = Parser.parse(userInput);
                ui.printLineBreak();
                command.execute(taskManager, ui);
                isExit = command.isExit();
            } catch (PrimeException e) {
                ui.printIndented(e.getMessage());
            } catch (NumberFormatException e) {
                ui.printIndented("Please enter a valid number!");
            } finally {
                ui.printLineBreak();
            }
        }
        scanner.close();
    }
}
