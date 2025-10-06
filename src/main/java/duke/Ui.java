package duke;

import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * Responsible for reading input and displaying messages.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads input from the user.
     *
     * @return String-command entered by the user
     */
    public String readCommand(){
        return scanner.nextLine();
    }

    /** Shows the welcome message to the user. */
    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Gordon_Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }
    /** Shows an goodbye message when tasks fail to load. */
    public void showGoodbye(){
        System.out.println(" Bye. Hope to see you again soon!");
    }
    /**
     * Shows a message to the user.
     *
     * @param message the message to display
     */
    public void showMessage(String message){
        System.out.println(message);
    }
    /** Shows an error message when tasks fail to load. */
    public void showError(String message){
        System.out.println(message);
    }
    /**
     * Shows a dummy line
     */
    public void showDummyLine(){
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println();
    }
}
