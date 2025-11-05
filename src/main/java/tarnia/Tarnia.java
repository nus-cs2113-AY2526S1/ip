package tarnia;
import java.util.Scanner;

/**
 * The main class for the Tarnia application.
 * Initialises core components and runs the main program loop.
 * Handles user input and coordinates task management.
 */
public class Tarnia {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private CommandManager commands;

    public Tarnia(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage, ui);
        commands = new CommandManager(tasks, ui);
    }

    /**
     * Runs the main program loop.
     * Continuously takes in user input from the console and passes it 
     * to the CommandManager for handling.
     */
    public void run() {
        ui.printHelloMessage();
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine().trim();
            if (!commands.handleCommand(line)) {
                break;
            }
        }
        in.close();
    }

    /**
     * The entry point of the program.
     * Creates a new {@code Tarnia} instance with the file save path.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Tarnia("./data/tarnia.txt").run();
    }
}

    




