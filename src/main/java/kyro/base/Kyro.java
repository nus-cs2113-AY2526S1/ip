package kyro.base;

import kyro.exceptions.*;

import java.util.Scanner;

/**
 * Main entry point for the Kyro application.
 * <p>
 * This class initializes the task list, storage, and printer, and
 * coordinates user input parsing and command execution.
 */
public class Kyro {

    private final Printer printer;
    private final Storage storage;
    private final TaskList tasks;
    private final Scanner scanner;

    /**
     * Constructs a new Kyro instance.
     *
     * @param filePath File path used for loading and saving tasks.
     */
    public Kyro(String filePath) {
        printer = new Printer();
        storage = new Storage(filePath);
        tasks = storage.load();
        scanner = new Scanner(System.in);
    }

    /**
     * Runs the main loop of the Kyro application.
     * <p>
     * Continuously reads user input, parses it into commands,
     * and executes them until command BYE is given.
     */
    public void run() {
        printer.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = scanner.nextLine();
                printer.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, printer, storage);
                isExit = command.isExit();
            } catch (KyroException e) {
                printer.showError(e.getMessage());
            } catch (NumberFormatException e) {
                printer.showError("Kyro thinks you need to go back to school to learn what numbers are...");
            } finally {
                printer.showLine();
            }
        }
    }

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Kyro("./data/kyro.txt").run();
    }
}