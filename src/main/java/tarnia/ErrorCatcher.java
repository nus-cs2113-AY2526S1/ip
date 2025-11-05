package tarnia;

/**
 * Provides static methods to process and validate user commands,
 * handle errors, and display appropriate messages using the Ui class.
 * Each method corresponds to a specific command and ensures
 * correct input format and error handling for task operations.
 */
public class ErrorCatcher {

    /**
     * Process the "mark" and "unmark" command from user input.
     * Validates the provided task index, updates the corresponding task,
     * and handles any errors by printing appropriate messages.
     * 
     * @param parts The split user input.
     * @param manager The task list manager used to update the task.
     * @param command The command word, either "mark" or "unmark".
     * @param ui The user interface used for messages responding to user.
     */
    public static void catchMarkCommand(String[] parts, TaskList manager, String command, Ui ui) {
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if ("mark".equals(command)) {
                manager.markTask(index);
            } else {
                manager.unmarkTask(index);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printEmptyMessage(command);
        } catch (NumberFormatException e) {
            ui.printNotANumber(command);
        } catch (IndexOutOfBoundsException e) {
            ui.printOutOfRange(command);
        }
    }

    /**
     * Process the "todo" command from user input.
     * Validates the format, which must contain a message after the command.
     * Updates the task list by appending the todo task.
     * Handles any errors by printing appropriate messages.
     * 
     * @param parts The split user input.
     * @param manager The task list manager used to update the task.
     * @param ui The user interface used for messages responding to user.
     */
    public static void catchTodoCommand(String[] parts, TaskList manager, Ui ui) {
        try {
            manager.addTask(Parser.parseToDo(parts[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printEmptyMessage("todo");
        }
    }

    /**
     * Process the "deadline" command from user input.
     * Validates the format, which must contain a message and due-date.
     * Updates the task list by appending the deadline task.
     * Handles any errors by printing appropriate messages.
     * 
     * @param parts The split user input.
     * @param manager The task list manager used to update the task.
     * @param ui The user interface used for messages responding to user.
     */
    public static void catchDeadlineCommand(String[] parts, TaskList manager, Ui ui) {
        try {
            manager.addTask(Parser.parseDeadline(parts[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printEmptyMessage("deadline");
        } catch (IllegalArgumentException e) {
            if ("badformat".equals(e.getMessage())) {
                ui.printBadDeadlineFormat();
            }
        }
    }

    /**
     * Process the "event" command from user input.
     * Validates the format, which must contain a message,
     * start time, and end time.
     * Updates the task list by appending the event task.
     * Handles any errors by printing appropriate messages.
     * 
     * @param parts The split user input.
     * @param manager The task list manager used to update the task.
     * @param ui The user interface used for messages responding to user.
     */
    public static void catchEventCommand(String[] parts, TaskList manager, Ui ui) {
        try {
            manager.addTask(Parser.parseEvents(parts[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printEmptyMessage("event");
        } catch (IllegalArgumentException e) {
            if ("badformat".equals(e.getMessage())) {
                ui.printBadEventFormat();
            }
        }
    }

    /**
     * Process the "delete" command from user input.
     * Validates the format, which must contain an index.
     * Updates the task list by deleting the task of interest from the list.
     * Handles any errors by printing appropriate messages.
     * 
     * @param parts The split user input.
     * @param manager The task list manager used to update the task.
     * @param ui The user interface used for messages responding to user.
     */
    public static void catchDeleteCommand(String[] parts, TaskList manager, Ui ui) {
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            manager.deleteTask(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printEmptyMessage("delete");
        } catch (NumberFormatException e) {
            ui.printNotANumber("delete");
        } catch (IndexOutOfBoundsException e) {
            ui.printOutOfRange("delete");
        }
    }
    
    /**
     * Process the "find" command from user input.
     * Validates the format, which must contain a keyword.
     * Finds the tasks that contain the keyword, and outputs them out.
     * Handles any errors by printing appropriate messages.
     * 
     * @param parts The split user input.
     * @param manager The task list manager used to update the task.
     * @param ui The user interface used for messages responding to user.
     */
    public static void catchFindCommand(String[] parts, TaskList manager, Ui ui) {
        try {
            manager.findTask(parts[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printEmptyMessage("find");
        }
    }
}
