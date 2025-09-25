package Nova.ui;

import Nova.exception.NovaException;
import Nova.parser.Parser;
import Nova.command.Command;
import Nova.storage.Storage;
import Nova.task.TaskList;
import Nova.ui.TextUi;


public class Nova {
//    private static final String FilePath = "./NovaData/Nova.txt";
    private final Storage storage;
    private TaskList tasks;
    private final TextUi ui;

    public Nova(String filePath) {
        ui = new TextUi();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

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