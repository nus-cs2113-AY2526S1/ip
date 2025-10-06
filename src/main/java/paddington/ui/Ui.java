package paddington.ui;

/**
 * The Ui Class provides user interface methods for the Paddington chatbot.
 * Handles printing messages such as welcome, error, task operations and goodbye.
 */
public class Ui {
    static final String line = "____________________________________________________________\n";

    /**
     * Prints a horizontal line to the console.
     */
    public static void printHorizontalLine() {
        System.out.print(line);
    }

    /**
     * Prints a welcome message and the Paddington logo to the console.
     */

    public static void printWelcomeMessage() {
        final String logo = "                   _\n" +
                "            .--,-\"\" \"\"-.\n" +
                "           (            )\n" +
                "            j-  __.    |\n" +
                "            |  '     -'|\n" +
                "            |`--._____,+-.\n" +
                "          .';`--.___.'    `.\n" +
                "        .'         /    _  |\n" +
                "       /        ,-' _.-'o;__\\\n" +
                "      (           .' o.-'  \\)`.\n" +
                "       `j--..___.'   '     /   )\n" +
                "      (/   _    `-._    `-/`--' ,----.\n" +
                "      (.'   `--. `. `-._.'`._,-<o ===|\n" +
                "     .'         \\  `.  /\\`./`.  `----'\n" +
                "   .'            |   `' _\\,() \\\n" +
                " .'          ;_)      ()___/   \\\n" +
                "/   `._____.'             |     \\\n" +
                "\\       /    =====.       \\  _   `.\n" +
                " `.____/  /        \\     __\\/()    \\\n" +
                "     .'  (          )  ()____/     .'\n" +
                "    /     `---    -'        |\\   .'\n" +
                "    `---.___                | `-'\n" +
                "          .-`--.________.---'`.\n" +
                "       .-'              /      \\\n" +
                "      /               .'        \\\n" +
                "     /            _.-'\\          |\n" +
                "     |  `-.____.-'     `.        ; .-\"\"-.\n" +
                "     |      |            `.      ,'     |\n" +
                "     \\      |              \\            /\n" +
                "      `.___.'               `.         /\n" +
                "                              \\      .'\n" +
                "                               `.__.'\n";

        printHorizontalLine();
        System.out.print(logo);
        System.out.println("Hello! I'm Paddington.");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints an error description message to the console.
     *
     * @param errorDescription the error message to display
     */
    public static void printErrorDescription(String errorDescription) {
        System.out.println("(!) Error: " + errorDescription);
    }

    /**
     * Prints a goodbye message when the program terminates.
     */
    public static void printGoodbye() {
        System.out.println("Mind the gap between the train and the platform.");
        System.out.println("This is Paddington, where this train terminates. All change.");
        System.out.println("Here's some marmalade and see you soon!");
    }

    /**
     * Prints a task description to the console.
     *
     * @param taskString the task to display
     */
    public static void printTask(String taskString) {
        System.out.println("  " + taskString);
    }

    /**
     * Prints a message when a task is added to the list.
     *
     * @param taskString the task that was added
     * @param taskCount the current number of tasks in the list
     */
    public static void printAddTask(String taskString, int taskCount) {
        System.out.println("Got it. I've added this task:");
        printTask(taskString);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints a message when a task is deleted from the list.
     *
     * @param taskString the task that was removed
     * @param taskCount the current number of tasks in the list
     */
    public static void printDeleteTask(String taskString, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        printTask(taskString);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param taskString the task that was marked as done
     */
    public static void printMarkTask(String taskString) {
        System.out.println("Nice! I've marked this task as done:");
        printTask(taskString);
    }

    /**
     * Prints a message when a task is marked as not done.
     *
     * @param taskString the task that was unmarked
     */
    public static void printUnmarkTask(String taskString) {
        System.out.println("OK, I've marked this task as not done yet");
        printTask(taskString);
    }

}
