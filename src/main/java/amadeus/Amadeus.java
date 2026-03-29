package amadeus;

import java.util.Scanner;
import java.io.IOException;

/**
 * This is main class of Amadeus program.
 * It manages the storage, the tasks and the user interface.
 * Also this class run the main loop for command processing.
 */
public class Amadeus {
    /** Storage object to save and load tasks from file */
    private Storage storage;

    /** List of tasks currently in memory */
    private TaskList taskList;

    /** User interface to show messages and read commands */
    private Ui ui;

    /**
     * Creates new Amadeus object with file path to save/load tasks.
     * If loading failed, it create empty task list.
     *
     * @param filePath path of file where tasks are saved
     */
    public Amadeus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs main program loop.
     * It show logo, greeting and then wait for commands from user.
     * Program continue until user types "Disconnect".
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showLogo();
        ui.greet();

        while (true) {
            String command = scanner.nextLine().trim();
            System.out.println();

            if (command.equalsIgnoreCase(Commands.DISCONNECT)) {
                ui.showShutdown();
                break;
            }

            try {
                Parser.parse(command, scanner, taskList, storage, ui);
            } catch (AmadeusException | IOException e) {
                ui.printSeparator();
                System.out.println(e.getMessage());
                ui.printSeparator();
            }
        }

        scanner.close();
    }

    /**
     * Main method for start program.
     * It create Amadeus instance and run it.
     *
     * @param args command line arguments, not used
     */
    public static void main(String[] args) {
        new Amadeus("./data/amadeus.txt").run();
    }
}
