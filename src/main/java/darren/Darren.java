package darren;

import darren.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Initialises main components such as UI, storage and task list, and manages running of the main command loop.
 */
public class Darren {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Darren application instance.
     * Initialises UI and storage. Loads tasks from storage if available, else it starts with an empty task list.
     * @param filePath The path to the file where tasks are stored.
     */
    public Darren(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DarrenException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Starts the main execution loop of the chatbot.
     * Continuously reads user inputs, processes commands through the Parser and handles any exceptions.
     */
    public void run() {
        this.ui.displayWelcome();

        boolean isExit = false;

        while (!isExit) {
            try {
                String input = this.ui.nextLine();

                Parser.command(input, this.tasks, this.ui, this.storage);

                isExit = Parser.exit(input);
            } catch (DarrenException e) {
                this.ui.displayError("Uh oh! " + e.getMessage());
            } catch (IOException e) {
                this.ui.displayError("A serious file error occurred: " + e.getMessage());
                isExit = true;
            }
        }

        this.ui.displayBye();
        this.ui.close();
    }

    public static void main(String[] args) {
        new Darren("data/tasks.txt").run();
    }
}
