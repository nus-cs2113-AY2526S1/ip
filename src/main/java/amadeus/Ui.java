package amadeus;

/**
 * This class is responsible to show messages to user.
 * It display logo, greetings, separators, task updates, and errors.
 */
public class Ui {

    /**
     * Show the Amadeus ASCII logo.
     */
    public static void showLogo() {
        String logo = """
                ╔══════════════════════════════════════════════════════════════╗
                ║                                                              ║
                ║  █████╗ ███╗   ███╗ █████╗ ██████╗ ███████╗██╗   ██╗███████╗ ║
                ║ ██╔══██╗████╗ ████║██╔══██╗██╔══██╗██╔════╝██║   ██║██╔════╝ ║
                ║ ███████║██╔████╔██║███████║██║  ██║█████╗  ██║   ██║███████╗ ║
                ║ ██╔══██║██║╚██╔╝██║██╔══██║██║  ██║██╔══╝  ██║   ██║╚════██║ ║
                ║ ██║  ██║██║ ╚═╝ ██║██║  ██║██████╔╝███████╗╚██████╔╝███████║ ║
                ║ ╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚═════╝ ╚══════╝ ╚═════╝ ╚══════╝ ║
                ║                                                              ║
                ╚══════════════════════════════════════════════════════════════╝
                """;
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Show greeting message and instructions to user.
     * Tell the available modes and ask user to choose one.
     */
    public static void greet() {
        printSeparator();
        System.out.println("👋 Greetings. I am Amadeus, your cognitive assistant.");
        System.out.println("I monitor divergences in world lines… and sometimes your mistakes.");
        System.out.println("What knowledge do you seek today?");
        System.out.println();
        System.out.println("Here are the different modes : 1. Echo");
        System.out.println("                               2. D-mail");
        System.out.println("                               3. List");
        System.out.print("Write the name of the command you wish to use : ");
    }

    /**
     * Print a separator line.
     * Useful for better visibility in console.
     */
    public static void printSeparator() {
        System.out.println("────────────────────────────────────────────────────────────────");
    }

    /**
     * Show shutdown message.
     * Display separator and goodbye text.
     */
    public static void showShutdown() {
        printSeparator();
        System.out.println("System shutting down... awaiting next transmission.");
        System.out.println("El Psy Kongroo.");
        printSeparator();
    }

    /**
     * Show that a task is updated (marked or unmarked).
     *
     * @param msg message to show before task
     * @param task task that was updated
     */
    public static void taskUpdated(String msg, Task task) {
        printSeparator();
        System.out.println(msg);
        System.out.println("  " + task);
        printSeparator();
    }

    /**
     * Show that a task is added to list.
     *
     * @param tasks list of tasks after adding
     */
    public static void taskAdded(java.util.List<Task> tasks) {
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printSeparator();
    }

    /**
     * Show that a task is deleted from list.
     *
     * @param removed task that was removed
     * @param newSize new number of tasks in list
     */
    public static void taskDeleted(Task removed, int newSize) {
        printSeparator();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removed);
        System.out.println("Now you have " + newSize + " tasks in the list.");
        printSeparator();
    }

    /**
     * Show error message if tasks cannot be loaded from storage.
     */
    public void showLoadingError() {
        printSeparator();
        System.out.println("⚠️ Error while loading tasks. Starting with an empty list.");
        printSeparator();
    }
}
