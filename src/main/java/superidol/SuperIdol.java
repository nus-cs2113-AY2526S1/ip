package superidol;

import superidol.storage.Storage;

import superidol.command.Command;

import superidol.parser.Parser;

import superidol.tasklist.TaskList;
import superidol.ui.Ui;

import java.util.Scanner;

import java.io.File;


public class SuperIdol {

    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage("./todolist.txt", taskList);
    private static Parser parser = new Parser(taskList);

    public static void main(String[] args) {
        Ui.greeting();
        storage.loadSavedFile();
        Scanner in = new Scanner(System.in);

        while (true) {
            String command =  in.nextLine().trim();
            Command commandKeyWord = Command.getCommand(command);
            try {
                switch (commandKeyWord) {
                case EXIT:
                    exit(storage.getFile());
                    break;
                case LIST:
                    taskList.showList();
                    break;
                case MARK:
                    parser.mark(command, true);
                    break;
                case UNMARK:
                    parser.mark(command, false);
                    break;
                case TODO:
                    parser.addTodo(command);
                    break;
                case DEADLINE:
                    parser.addDeadline(command);
                    break;
                case EVENT:
                    parser.addEvent(command);
                    break;
                case DELETE:
                    parser.deleteTask(command);
                    break;
                default:
                    Ui.respondToInvalidCommand();
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.respondToInvalidCommand();
            }
        }
    }

    public static void exit(File f) {
        Ui.goodbye();
        storage.saveToFile(f);
        System.exit(0);
    }


}
