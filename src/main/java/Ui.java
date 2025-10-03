import java.util.Scanner;
/**
 * Console UI helper for reading user commands and printing
 * formatted messages, separators, and status updates.
 *
 * <p>This class owns a {@link Scanner} bound to {@code System.in} for
 * {@link #readCommand()} and provides convenience methods to keep output
 * consistent across the application.</p>
 *
 * <p><strong>Note:</strong> Do not close the internal {@code Scanner};
 * closing it would also close {@code System.in} for the entire JVM.</p>
 *
 */

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("-".repeat(30));
    }

    public void showEmptyList() {
        System.out.println("Shiok ah no tasks!");
    }

    public void showListHeader() {
        System.out.println("Here are your tasks bro:");
    }

    public void showTaskAdded(Task t, int size) {
        showLine();
        System.out.println("ok added the following task bro:");
        System.out.println(t);
        System.out.println("now you got " + size + " tasks in your list.");
        showLine();
    }

    public void showMarked(Task t, boolean done) {
        showLine();
        System.out.println(done ? "Nice la , I have marked it as done!" : "Shag , I have marked it as undone!");
        System.out.println(" " + t);
        showLine();
    }

    public void showBye() {
        System.out.println();
        showLine();
        System.out.println("Bye.Hope to see you again soon!\n");
        showLine();
    }

    public void showDelete(Task t, int size) {
        showLine();
        System.out.println("thy task have been vanquished!");
        System.out.println(t);
        System.out.println("now you only left with " + size + " tasks!");
        showLine();
        System.out.println(" ".repeat(30));
    }

    public void showAddedDeadline(Task t, int size) {
        showLine();
        System.out.println("okies brother. I added this deadline!");
        System.out.println(t);
        System.out.println("now you got: " + size + " tasks");
        System.out.println(" ");
        showLine();
    }

    public void showAddedEvent(Task t, int size) {
        System.out.println("okies brother. I added this event!");
        System.out.println(t);
        System.out.println("now you got: " + size + " tasks");
        System.out.println(" ");
        showLine();
    }

    public void showError(String msg) {
        System.out.println("siala got smt wrong:\n" + msg);
    }
}
