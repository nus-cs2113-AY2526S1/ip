package Chauncey.ui;

import Chauncey.task.*;
import Chauncey.exception.ChaunceyException;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

public class Chauncey {
//    private static ArrayList<Task> tasks = new ArrayList<>();
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Chauncey(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            ui.showLoadingMessage();
            tasks = new TaskList(storage.loadFile());
        } catch (ChaunceyException e) {
            ui.showErrorMessage(e);
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DateTimeParseException e) {
            ui.showDateFormatError();
            tasks = new TaskList();
        }
    }

    private void addTask() throws ChaunceyException, IOException {
        ui.showSelectTaskTypeMessage();
        String type = ui.readCommand();
        ui.showInputTaskDetailsMessage();
        Parser taskDetailsParser = new Parser(ui.readCommand());
        String[] taskDetails = taskDetailsParser.parseTaskDetails();
        tasks.addTask(type, taskDetails);
        ui.showTaskAddedMessage(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
        storage.saveToFile(tasks.getTasksList());
    }

    private void deleteTask(Parser commandParser) throws ChaunceyException, IOException {
        int taskNumber = commandParser.parseTaskNumber(tasks.getSize());
        String taskDetails = tasks.deleteTask(taskNumber);
        ui.showTaskDeletedMessage(taskDetails, tasks.getSize());
        storage.saveToFile(tasks.getTasksList());
    }

    private void markTask(Parser commandParser) throws ChaunceyException, IOException {
        int taskNumber = commandParser.parseTaskNumber(tasks.getSize());
        tasks.markTask(taskNumber);
        ui.showMarkTaskMessage(tasks.getTask(taskNumber - 1));
        storage.saveToFile(tasks.getTasksList());
    }

    private void unmarkTask(Parser commandParser) throws ChaunceyException, IOException {
        int taskNumber = commandParser.parseTaskNumber(tasks.getSize());
        tasks.unmarkTask(taskNumber);
        ui.showUnmarkTaskMessage(tasks.getTask(taskNumber - 1));
        storage.saveToFile(tasks.getTasksList());
    }

    private TaskList findTasks(Parser commandParser) throws ChaunceyException {
        String keyword = commandParser.parseKeyword();
        TaskList filteredTasks = new TaskList(tasks.filterTasks(keyword));
        return filteredTasks;
    }

    private void executeCommand(String command) {
        try {
            if (command.isEmpty()) {
                throw new ChaunceyException("Command is empty! Please input a command.");
            }
            Parser commandParser = new Parser(command);
            String instruction = commandParser.parseCommand();
            switch (instruction) {
            case "list":
                ui.listTasks(tasks);
                break;
            case "add":
                addTask();
                break;
            case "delete":
                deleteTask(commandParser);
                break;
            case "mark":
                markTask(commandParser);
                break;
            case "unmark":
                unmarkTask(commandParser);
                break;
            case "find":
                ui.listFilteredTasks(findTasks(commandParser));
                break;
            default:
                throw new ChaunceyException("Oh no! I don't know what the command means. Please input a valid command.");
            }
        } catch (ChaunceyException | IOException e) {
            ui.showErrorMessage(e);
        } catch (DateTimeParseException e) {
            ui.showDateFormatError();
        }
    }

    public void run() {
        ui.printLine();
        ui.printWelcomeMessage();
        ui.printLine();
        ui.printEmptyLine();

        String command = ui.readCommand().toLowerCase();
        while (!command.equals("bye")) {
            ui.printLine();
            executeCommand(command);
            ui.printLine();
            ui.printEmptyLine();
            ui.askForNextCommand();
            command = ui.readCommand();
        }

        ui.printLine();
        ui.printExitMessage();
        ui.printLine();
    }

    public static void main(String[] args) {
        new Chauncey("./data/Chauncey.txt").run();
    }
}
