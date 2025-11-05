package tarnia;

/**
 * Manages usser commands, coordinates between TaskList and UI classes
 * to Process commands, update tasks, and display messages to user.
 */
public class CommandManager {
    private TaskList manager;
    private Ui ui;

    /**
     * Creates a CommandManager to handle user commands.
     * Initialises task list and UI for processing input and displaying messages.
     * 
     * @param manager The task list manager used to store and update tasks.
     * @param ui The message manager used for responding to user input.
     */
    public CommandManager(TaskList manager, Ui ui) {
        this.manager = manager;
        this.ui = ui;
    }

    /**
     * Handles a single line of user input by parsing the command word
     * and the rest to the appropriate handler.
     * 
     * @param line The raw user input String.
     * @return false if the command is "bye" and exits program,
     *         true otherwise and continues running.
     */
    public boolean handleCommand(String line) {
        String[] parts = line.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "list":
            ui.printTaskList(manager.getTasks());
            break;
        case "mark":
        case "unmark":
            ErrorCatcher.catchMarkCommand(parts, manager, command, ui);
            break;
        case "bye":
            ui.printGoodbyeMessage();
            return false;
        case "deadline":
            ErrorCatcher.catchDeadlineCommand(parts, manager, ui);
            break;
        case "event":
            ErrorCatcher.catchEventCommand(parts, manager, ui);
            break;
        case "todo":
            ErrorCatcher.catchTodoCommand(parts, manager, ui);
            break;
        case "find":
            ErrorCatcher.catchFindCommand(parts, manager, ui);
            break;
        case "delete":
            ErrorCatcher.catchDeleteCommand(parts, manager, ui);
            break;
        default:
            ui.printUnknownCommand();
            break;
        }
        return true;
    }
}

