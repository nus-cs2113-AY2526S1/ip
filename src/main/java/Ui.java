import java.util.Scanner;

/**
 * Console-based user interface for Bob.
 */

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /** Prints the greeting message */
    public void showWelcome() {
        String logo =
                """
                         ____   ___   ____\s
                        | __ ) / _ \\ | __ )
                        |  _ \\| | | ||  _ \\
                        | |_) | |_| || |_) |
                        |____/ \\___/ |____/
                       \s""";

        System.out.println();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }


    /** Reads the next full line of user input */
    public String readCommand() {
        return sc.nextLine();
    }

    /** Prints a horizontal line to the console as design feature */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /** Showcases the error the user input */
    public void showError(String message) {
        System.out.println("There is error, please fix: " + message + "- Bob");
    }
}
