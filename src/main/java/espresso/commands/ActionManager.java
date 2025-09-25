package espresso.commands;

import java.util.Scanner;

import espresso.ui.Display;
import espresso.inputchecker.Parser;
import espresso.tasks.TaskManager;

/**
 * The {@code ActionManager} class handles the main interaction loop and delegates
 * user commands to the appropriate task management actions.
 */
public class ActionManager {


    /**
     * Starts the main input loop for the application.
     * Continuously reads user input from the console until the user types "bye".
     * Each input is parsed and executed as a command.
     */
    public static void mainLoop() {
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                return;
            }
            doAction(userInput);
        }
    }


    /**
     * Parses and executes a user command.
     * Delegates the command to the appropriate method in {@link TaskManager}.
     *
     * @param userInput the raw input string entered by the user
     */
    public static void doAction(String userInput) {
        Command command = Parser.parseInput(userInput);

        switch (command.getType()) {
        case LIST:
            TaskManager.printTasks();
            break;
        case MARK:
            TaskManager.markTask(true, command.getTaskIndex());
            break;
        case UNMARK:
            TaskManager.markTask(false, command.getTaskIndex());
            break;
        case DELETE:
            TaskManager.deleteTask(command.getTaskIndex());
            break;
        case TODO:
            TaskManager.addTodo(command.getDescription());
            break;
        case DEADLINE:
            TaskManager.addDeadline(command.getDescription(), command.getDeadline());
            break;
        case EVENT:
            TaskManager.addEvent(command.getDescription(), command.getEventStart(), command.getEventEnd());
            break;
        case FIND:
            TaskManager.findTasks(command.getKeywords());
            break;
        case HELP:
            Display.printHelpMessage();
            break;
        case INVALID:
        default:
            Display.printInvalidCommand(command.getDescription());
            break;
        }
    }

}
