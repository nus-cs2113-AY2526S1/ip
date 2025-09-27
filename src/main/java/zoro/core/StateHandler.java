package zoro.core;

import zoro.ui.UserInterface;
import zoro.ui.InputHandler;



public class StateHandler {
    private final UserInterface ui;
    private final TaskManager taskManager;
    private final InputHandler inputHandler;

    public StateHandler(UserInterface ui, TaskManager taskManager, InputHandler inputHandler) {
        this.ui = ui;
        this.taskManager = taskManager;
        this.inputHandler = inputHandler;
    }


    public State handleMenu() {
        ui.printMenuIntro();
        String userInput = inputHandler.getUserInput();
        if (userInput == null) {
            return State.EXIT;
        }
        switch (userInput) {
            case "echo":
            case "1":
                return State.ECHO;
            case "list":
            case "task":
            case "todo":
            case "2":
            case "store list":
                return State.TASK;
            case "exit":
            case "bye":
                ui.printGoodbye();
                return State.EXIT;
            default:
                ui.printInvalidInput();
                return State.MENU;
        }
    }

    public State handleEcho() {
        ui.printEchoInstruction();
        while (true) {
            String userInput = inputHandler.getUserInput();
            if (userInput == null) {
                return State.EXIT;
            }
            switch (userInput) {
                case "bye":
                case "exit":
                    ui.printGoodbye();
                    return State.EXIT;
                case "menu":
                case "return":
                case "back":
                    return State.MENU;
                default:
                    ui.printEchoMessage(userInput);
            }
        }
    }

    public State handleTask() {
        ui.printTaskInstruction();
        while (true) {
            String userInput = inputHandler.getUserInput();
            if (userInput == null) {
                return State.EXIT;
            }
            String userCommand = userInput.split(" ")[0];
            switch (userCommand) {
            case "todo":
                taskManager.processTodoCommand(userInput, ui);
                break;
            case "deadline":
                taskManager.processDeadlineCommand(userInput, ui);
                break;
            case "event":
                taskManager.processEventCommand(userInput, ui);
                break;
            case "list":
                ui.printTaskList(taskManager.getTasks());
                break;
            case "mark":
                taskManager.processMarkCommand(userInput, ui);
                break;
            case "delete":
                taskManager.processDeleteCommand(userInput, ui);
                break;
            case "find":
                taskManager.processFindCommand(userInput, ui);
                break;
            case "menu":
            case "back":
            case "return":
            case "zoro":
                return State.MENU;
            case "bye":
                ui.printGoodbye();
                return State.EXIT;
            default:
                ui.printInvalidInput();
            }
        }
    }

}
