package superidol;

import superidol.command.Command;
import superidol.storage.Storage;

import superidol.parser.Parser;

import superidol.tasklist.TaskList;
import superidol.ui.Ui;

import java.util.Scanner;

/**
 * SuperIdol class
 */
public class SuperIdol {

    private final TaskList taskList;
    private final Storage storage;

    public SuperIdol(String filePath) {
        this.taskList = new TaskList();
        this.storage = new Storage(filePath, this.taskList);
    }

    /**
     * run loop
     * get raw command
     * pass to parser
     * execute command
     */
    public void run(){
        Ui.greeting();
        storage.loadSavedFile();
        Scanner in = new Scanner(System.in);

        boolean isExit = false;

        while (!isExit) {
            String command =  in.nextLine().trim();
            Command c = Parser.parse(command);
            if (c != null) {
                c.execute(taskList, storage);
                isExit = c.getIsExit();
            }
        }
    }

    public static void main(String[] args) {
        new SuperIdol("./todolist.txt").run();
    }

}
