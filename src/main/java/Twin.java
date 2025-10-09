import command.Command;
import exception.TwinException;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import task.TaskList;

/**
 * Test class to run the Twin task management application.
 * Handles initialization of UI, storage, and task list,
 * and manages the main input loop.
 */
public class Twin {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Test instance with the specified file path for storage.
     *
     * @param filePath the file path to load/save tasks
     */
    public Twin(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TwinException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application:
     * shows the welcome message, reads user commands,
     * parses and executes them until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(ui, storage, tasks);
                isExit = c.isExit();
            } catch (TwinException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main entry point of the application.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        new Twin("data/twin.txt").run();
    }
}
