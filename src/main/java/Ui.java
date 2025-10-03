import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

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


    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("There is error, please fix: " + message + "- Bob");
    }

    public void showLoadingError() {
        System.out.println("Error, please try again - Bob ðŸ˜¡");
    }
}
