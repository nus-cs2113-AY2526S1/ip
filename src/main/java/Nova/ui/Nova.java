package Nova.ui;

import Nova.exception.NovaException;
import Nova.parser.Parser;
import Nova.command.Command;
import Nova.storage.Storage;
import Nova.task.TaskList;


/**
 * Represents the main Nova application that ties together
 * the UI, task list, and storage components.
 * <p>
 * It is responsible for initializing the application and
 * running the main input loop.
 */
public class Nova {
    private final Storage storage;
    private TaskList tasks;
    private final TextUi ui;

    /**
     * Constructs a Nova application with a specified file path for storage.
     * Initializes the UI, Storage, and loads tasks from file.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Nova(String filePath) {
        ui = new TextUi();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     * Continuously reads user input, parses it into commands,
     * executes the commands, and exits when an exit command is issued.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parseCommand(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (NovaException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}