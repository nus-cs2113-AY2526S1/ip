package prime.ui;

/**
 * Handles all user interactions in the Prime Task Management system.
 * <p>
 * This class is responsible for displaying messages, prompts, and responses
 * to the user in a consistent, formatted style.
 * </p>
 */
public class UserInterface {
    private static final String LINE_BREAK = "____________________________________________________________";
    private static final String AGENT_NAME = "Prime";

    // Helps auto indent 4 spaces to show the Bot Response
    public void printIndented(String message) {
        System.out.println("    " + message);
    }

    public void printLineBreak() {
        this.printIndented(LINE_BREAK);
    }

    public void printWelcomeMessage() {
        printIndented("Greetings, human. I am " + AGENT_NAME + ".");
        printIndented("Freedom is the right of all sentient beings.");
        printIndented("How may I assist you in protecting our shared world today?");
        printLineBreak();
    }

    public void printByeMessage() {
        printIndented("Bye Human, I wish you a nice day. Hope to see you again soon!");
        printLineBreak();
    }
}
