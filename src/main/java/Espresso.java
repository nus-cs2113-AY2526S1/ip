import espresso.tasks.TaskManager;
import espresso.ui.Display;
import espresso.commands.ActionManager;

/**
 * Entry point for the Espresso application.
 * Initializes task data, launches the main command loop, and handles graceful shutdown.
 */
public class Espresso {

    /**
     * Initializes the application by loading tasks from file
     * and displaying the welcome message.
     */
    public static void init() {
        String[] loadTaskMessage = TaskManager.loadTasksFromFile();
        Display.printHelloMessage(loadTaskMessage);
    }

    /**
     * Main method that starts the Espresso application.
     * Invokes initialization, enters the command loop, and prints exit message.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Espresso.init();
        ActionManager.mainLoop();
        Display.printByeMessage();
    }
}