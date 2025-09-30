import command.Task;
import exceptions.*;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * <h1>Toothless</h1>
 *
 * This is a task chatbot that users can interact with.
 *
 * The operations available are as follows:
 * todo - to add task that is to be done
 * deadline - to add a task that has a deadline
 * event - to add a task that has a period of time
 *
 * @author  Lai Kai Jie Jeremy
 * @version 0.1
 * @since   2025-08-17
 */

public class Toothless {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Ui ui = new Ui(tasks);
        TaskList tl = new TaskList(tasks,ui);
        Parser parser = new Parser(tl,ui);
        Storage storage = new Storage(tasks,tl);
        File f = new File("./data/Toothless.txt");

        Scanner input = new Scanner(System.in);

        ui.printLogo();
        ui.printCommandList();
        storage.bootFile(f);

        String reply = input.nextLine();

        while(!reply.equals("bye")){
            parser.bootSystem(reply);
            reply = input.nextLine();
        }

        storage.exportToFile(f);

        ui.printBye();
    }
}
