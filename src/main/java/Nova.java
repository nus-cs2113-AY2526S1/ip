import java.util.Scanner;

public class Nova {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                ____________________________________________________________
                Hello! I'm Nova
                What can I do for you?
                ____________________________________________________________
                """);

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("""
                        ____________________________________________________________
                         Bye. Hope to see you again soon!
                        ____________________________________________________________
                        """);
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println(" " + userInput);
            System.out.println("___________________________________________________________");
        }
    }
}
