import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

import storage.TaskStorage;
import exceptions.EmptyDescriptionException;
import exceptions.WrongTaskNumberException;
import tasks.Task;

import parser.Parser;

/**
 * Main class for the task management application. Keeps the program lifecycle and
 * persistence responsibilities while delegating parsing and command execution to
 * Parser.
 */
public class Bruh {

    public static final String filePath = "./data/tasks.txt";

    public static void main(String[] args) {

        String logo = """
                Hello! I'm:
                 ____  ____  _   _ _   _\s
                | __ )|  _ \\| | | | | | |
                |  _ \\| |_) | | | | |_| |
                | |_) |  _ <| |_| |  _  |
                |____/|_| \\_\\\\___/|_| |_|\
                
                Type something:\s
                """;
        System.out.println(logo);

        Scanner in = new Scanner(System.in);
        String line;

        TaskStorage storage = new TaskStorage(filePath);
        ArrayList<Task> tasks;

        try {
            System.out.println("Loading tasks from " + filePath);
            tasks = storage.loadTasks();
        } catch (EmptyDescriptionException | IOException e) {
            System.out.println("Problem reading " + filePath);
            return;
        }

        while (true) {
            line = in.nextLine();

            System.out.println("-----------------------------------------------------------------------------");
            if (line.isBlank()) {
                System.out.println("Please enter something");
                continue;
            }
            String[] input = line.split(" ", 2);

            try {
                Parser.Result result = Parser.parse(storage, tasks, input);

                if (result.modified) {
                    try {
                        storage.saveTasks(tasks);
                    } catch (IOException e) {
                        System.out.println("Failed to save tasks :(( " + e.getMessage());
                    }
                }

                if (result.exit) {
                    return;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Missing arguments!");
            } catch (EmptyDescriptionException e) {
                System.out.println("An input field is empty, try again...");
            } catch (WrongTaskNumberException e) {
                if (tasks.isEmpty()) {
                    System.out.println("You have no tasks");
                } else {
                    System.out.println("You entered an invalid task number; choose tasks 1 to " + tasks.size());
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid dateTime format. Expected: dd/MM/yyyy HHmm (time optional)");
            } catch (IOException e) {
                System.out.println("Error getting tasks from data");
            }

            System.out.println("-----------------------------------------------------------------------------");
        }
    }
}
