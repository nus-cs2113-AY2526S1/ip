package duke;

import duke.command.Command;



/**
 * This is the main class for running the program
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Contructing a Duke instance by the given file folder to restore information
     * @param filePath the file path to load and save tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * function to run the chatbot, controling UI, Parser and Storage to handle the input
     *
     * @throws DukeException when there are mistakes when implementing inputs in a lower level
     */

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDummyLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDummyLine();
            }
        }
    }
    /**
     * Main function, the "core" of the whole program
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
