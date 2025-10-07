package rudeus;

import rudeus.command.CommandManager;
import rudeus.task.TaskManager;
import rudeus.ui.Ui;

/**
 * Entry point for the Rudeus application.
 */
public class Rudeus {
    /**
     * Main method to start the Rudeus application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        try {
            Ui.printGreetingMessage();
            TaskManager taskManager = new TaskManager("./data/tasks.txt");
            CommandManager commandManager = new CommandManager(taskManager);
            commandManager.readAndProcessUserInput();
        } catch (Exception e) {
            Ui.printMessageWithBorders("An unexpected error occurred: " + e.getMessage());
        }
    }
}
