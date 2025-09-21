package prime;

import prime.exceptions.PrimeException;
import prime.command.Command;
import prime.parser.Parser;
import prime.manager.TaskManager;
import prime.ui.UserInterface;

import java.util.Scanner;

public class Prime {

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
