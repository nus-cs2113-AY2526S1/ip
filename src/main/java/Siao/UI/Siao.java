package Siao.UI;
import Siao.Command.CommandHandler;
import Siao.management.Constants;
import Siao.task.Task;
import Siao.management.Storage;


import java.util.Scanner;
import java.util.ArrayList;

public class Siao {

    /**
     * Entry point of the application. Initializes essential components such as
     * the storage handler and task list, then continuously reads and processes
     * user commands from standard input until the user enters "bye"
     *
     * @param args input given by users
     */

    public static void main(String[] args) {
        System.out.print(Constants.WELCOME_MESSAGE);

        Storage storage = new Storage("data/SavedTasks.txt");
        CommandHandler.setStorage(storage);

        Scanner input = new Scanner(System.in);
        ArrayList<Task> list = storage.loadTasks();
        String line = input.nextLine();

        while(!line.toLowerCase().contains("bye")){
            try {
                CommandHandler.handleCommand(line, list);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.print(">>>   ");
            }
            line = input.nextLine();
        }

        System.out.println(Constants.DIVIDER);
        System.out.println(Constants.GOODBYE_MESSAGE);
    }
}
