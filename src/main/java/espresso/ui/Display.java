package espresso.ui;

/**
 * Handles all user-facing output for the Espresso application.
 * Provides formatted messages, banners, and feedback for command execution.
 */
public class Display {

    /** Divider line used to visually separate message blocks. */
    private static final String DIVIDER = "\t____________________________________________________________";

    /** ASCII logo displayed at startup. */
    private static final String LOGO = """
            .·:''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''':·.
            : : ███████╗███████╗██████╗ ██████╗ ███████╗███████╗███████╗ ██████╗  : :
            : : ██╔════╝██╔════╝██╔══██╗██╔══██╗██╔════╝██╔════╝██╔════╝██╔═══██╗ : :
            : : █████╗  ███████╗██████╔╝██████╔╝█████╗  ███████╗███████╗██║   ██║ : :
            : : ██╔══╝  ╚════██║██╔═══╝ ██╔══██╗██╔══╝  ╚════██║╚════██║██║   ██║ : :
            : : ███████╗███████║██║     ██║  ██║███████╗███████║███████║╚██████╔╝ : :
            : : ╚══════╝╚══════╝╚═╝     ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝ ╚═════╝  : :
            '·:...................................................................:·'
            """;

    /**
     * Prints the Espresso ASCII logo banner.
     * Used during startup to visually brand the application.
     */
    private static void printBanner() {
        System.out.println("\n" + LOGO);
    }

    /**
     * Displays the welcome message and task loading status.
     *
     * @param loadTaskMessage array (of length 2) containing status messages about task loading
     */
    public static void printHelloMessage(String[] loadTaskMessage) {
        printBanner();
        printMessage("Hello! I'm ESPRESSO",
                "What can I do for you today?",
                loadTaskMessage[0],
                loadTaskMessage[1]);
    }

    /**
     * Displays the farewell message when the user exits the application.
     */
    public static void printByeMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a formatted error message for invalid commands.
     *
     * @param specificErrorMessage additional context about the invalid input
     */
    public static void printInvalidCommand(String specificErrorMessage) {
        printMessage("Invalid command",
                "Please use todo, deadline or event to add a task",
                "Example: todo borrow book",
                specificErrorMessage);
    }

    /**
     * Displays one or more lines of text wrapped in a visual divider.
     *
     * @param message variable number of message lines to display
     */
    public static void printMessage(String... message) {
        System.out.println(DIVIDER);
        for (String s : message) {
            System.out.println("\t" + s);
        }
        System.out.println(DIVIDER);
    }

    public static void printHelpMessage() {
        printMessage("Refer to https://xelisce.github.io/ip/ for more information on how to use commands.");
    }
}