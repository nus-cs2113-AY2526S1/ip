package amadeus;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class handle parsing of commands from user.
 * It decide what mode to run based on input command.
 */
public class Parser {

    /**
     * Parse user command and run proper mode.
     * If command unknown it throws exception.
     *
     * @param command input from user
     * @param scanner scanner to read user input in modes
     * @param taskList list of tasks to operate on
     * @param storage storage object to save/load tasks
     * @param ui user interface to show messages
     * @throws AmadeusException if command not known
     * @throws IOException if saving or loading tasks fails
     */
    public static void parse(String command, Scanner scanner, TaskList taskList, Storage storage, Ui ui)
            throws AmadeusException, IOException {

        switch (command) {
        case Commands.ECHO -> runEchoMode(scanner, ui);
        case Commands.DMAIL -> runDmailMode(scanner, ui);
        case Commands.LIST -> runListMode(scanner, taskList, storage, ui);
        case "Find" -> runFindMode(scanner, taskList);
        default -> throw new AmadeusException("⚠️ Unknown command. Try again.");
        }
    }

    /**
     * Runs echo mode, it just repeat what user type.
     * User can type 'Esc' to exit this mode.
     *
     * @param scanner scanner for user input
     * @param ui user interface to show messages
     */
    private static void runEchoMode(Scanner scanner, Ui ui) {
        System.out.println("The Mad Scientist chose the option Echo");
        System.out.println("Echo mode activated. Type 'Esc' to exit.");
        while (true) {
            String input = scanner.nextLine();
            if ("Esc".equalsIgnoreCase(input)) {
                Ui.showShutdown();
                break;
            }
            Ui.printSeparator();
            System.out.println("You just said: " + input);
            Ui.printSeparator();
        }
    }

    /**
     * Runs D-mail mode, user can send message to past.
     * Type 'El Psy Kongroo' to exit mode safely.
     *
     * @param scanner scanner for user input
     * @param ui user interface to show messages
     */
    private static void runDmailMode(Scanner scanner, Ui ui) {
        System.out.println("The Mad Scientist chose to send a D-mail");
        System.out.println("D-mail mode activated. Type 'El Psy Kongroo' to exit.");
        while (true) {
            String input = scanner.nextLine();
            if ("El Psy Kongroo".equalsIgnoreCase(input)) {
                Ui.printSeparator();
                System.out.println("The SERN is spying us, we need to disconnect...");
                System.out.println("⚠️ Your position has been compromised. Flee immediately.");
                System.out.println("El Psy Kongroo.");
                System.out.println("World line shift imminent.");
                Ui.printSeparator();
                break;
            }
            Ui.printSeparator();
            System.out.println("📱 D-mail is sending to the past...");
            System.out.println("⚡ Time transmission in progress...");
            System.out.println("📧 Message received in world line 1.130205%: " + input);
            Ui.printSeparator();
        }
    }

    /**
     * Runs list mode, it allow to manage tasks.
     * User can type 'Bye' to exit list mode.
     *
     * @param scanner scanner for user input
     * @param taskList list of tasks
     * @param storage storage to save tasks
     * @param ui user interface for messages
     * @throws IOException if saving tasks fails
     * @throws AmadeusException if command in list mode is unknown
     */
    private static void runListMode(Scanner scanner, TaskList taskList, Storage storage, Ui ui)
            throws IOException, AmadeusException {
        System.out.println("List mode activated. Type 'Bye' to exit.");
        while (true) {
            String input = scanner.nextLine();
            if ("Bye".equalsIgnoreCase(input)) {
                System.out.println("The list printing is finished");
                break;
            }
            taskList.handleCommand(input, storage, ui);
        }
    }

    /**
     * Runs find mode, allow user to search keyword in tasks.
     * Type 'Bye' to exit find mode.
     *
     * @param scanner scanner for user input
     * @param taskList list of tasks to search
     */
    private static void runFindMode(Scanner scanner, TaskList taskList) {
        System.out.println("Find mode activated. Type a keyword to search in tasks:");
        while (true) {
            String input = scanner.nextLine().trim();
            if ("Bye".equalsIgnoreCase(input)) {
                System.out.println("Exiting find mode.");
                break;
            }
            try {
                taskList.handleCommand("find " + input, null, null);
            } catch (AmadeusException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
