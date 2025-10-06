package paddington.parser;

import paddington.task.TaskList;
import paddington.ui.PaddingtonException;
import paddington.ui.Ui;

/**
 * Parses user input commands and interacts with the task list and UI.
 * The {@link Parser} class is responsible for interpreting user input, executing corresponding actions
 * (such as adding, deleting, or marking tasks), and handling errors related to command processing.
 */
public class Parser {
    private static boolean isQuit = false;
    private static boolean isTaskListChanged = false;

    /**
     * Returns whether the program should quit.
     *
     * @return true if the program should quit, false otherwise
     */
    public static boolean getIsQuit() {
        return isQuit;
    }

    /**
     * Sets the quit status of the program.
     *
     * @param isQuit true if the program should quit, false otherwise
     */
    public static void setIsQuit(boolean isQuit) {
        Parser.isQuit = isQuit;
    }

    /**
     * Returns whether the task list has been modified.
     *
     * @return true if the task list was modified, false otherwise
     */
    public static boolean getIsTaskListChanged() {
        return isTaskListChanged;
    }

    /**
     * Parses the user input and executes the corresponding action.
     * This method identifies the command and calls appropriate methods to handle the task list
     * or perform other actions, such as quitting the program or displaying tasks.
     *
     * @param input the user input containing the command and optional parameters
     * @throws PaddingtonException if there is an error with the input processing
     */
    public static void parseInput(String input) throws PaddingtonException {
        isTaskListChanged = false;

        String[] processedInput = input.split(" ", 2);
        String command = processedInput[0].toLowerCase();
        String params = (processedInput.length > 1) ? processedInput[1] : "";

        try {
            switch (command) {
                case "bye":
                    Ui.printGoodbye();
                    isQuit = true;
                    break;
                case "list":
                    TaskList.listAllTasks();
                    break;
                case "find":
                    TaskList.findTask(params);
                    break;
                case "mark":
                    TaskList.markTask(params);
                    isTaskListChanged = true;
                    break;
                case "unmark":
                    TaskList.unmarkTask(params);
                    isTaskListChanged = true;
                    break;
                case "delete":
                    TaskList.deleteTask(params);
                    isTaskListChanged = true;
                    break;
                case "todo":
                    TaskList.addTodo(params);
                    isTaskListChanged = true;
                    break;
                case "event":
                    TaskList.addEvent(params);
                    isTaskListChanged = true;
                    break;
                case "deadline":
                    TaskList.addDeadline(params);
                    isTaskListChanged = true;
                    break;
                default:
                    Ui.printErrorDescription("Invalid Command");
            }
        } catch (NumberFormatException e) {
            Ui.printErrorDescription("Index must be an integer.");
        } catch (IndexOutOfBoundsException e) {
            Ui.printErrorDescription("Invalid Index");
        }
    }
}
