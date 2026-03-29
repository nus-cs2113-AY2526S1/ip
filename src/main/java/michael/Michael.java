package michael;

import michael.Command.Command;
import michael.Parser.ParseInput;
import michael.TaskList.Task;
import michael.Storage.Storage;
import michael.Ui.UserMessages;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main entry point for the Michael task manager application.
 * Handles user interaction, command parsing, and task management loop.
 */
public class Michael {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int numberTasks = 0;
    public static String endingLine = "bye";
    public static String dataFile = "./data/data.txt";

    /**
     * Main method to start the Michael application.
     * Initializes UI, loads tasks from storage, and enters the command loop.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        UserMessages messages = new UserMessages();
        messages.welcomeMessage();

        Scanner in = new Scanner(System.in);
        String line = "";

        Storage storageFile = new Storage(dataFile);
        storageFile.getFileData();


        // Main loop: reads user input, parses commands, and executes them until 'bye' is entered
        while (!(line.equals(endingLine))) {

            line = in.nextLine();
            ParseInput parser = new ParseInput(line);
            Command command = parser.parse();
            command.executeCommand(tasks, messages, storageFile);

        }
    }
}
