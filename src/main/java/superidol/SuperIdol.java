package superidol;

import superidol.command.Command;
import superidol.storage.Storage;

import superidol.command.CommandKeyword;

import superidol.parser.Parser;

import superidol.tasklist.TaskList;
import superidol.ui.Ui;

import java.util.Scanner;


public class SuperIdol {

    private TaskList taskList;
    private Storage storage;

    public SuperIdol(String filePath) {
        this.taskList = new TaskList();
        this.storage = new Storage(filePath, this.taskList);
    }

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
