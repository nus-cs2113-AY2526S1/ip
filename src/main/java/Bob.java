
import java.util.Scanner;
public class Bob {

    public static void main(String[] args) {
        String logo =
                """
                         ____   ___   ____ \s
                        | __ ) / _ \\ | __ )\s
                        |  _ \\| | | ||  _ \\\s
                        | |_) | |_| || |_) |
                        |____/ \\___/ |____/\s
                        """;

        System.out.println();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        boolean endConvo = false;
        while (!endConvo) {
            Scanner scanner = new Scanner(System.in);
            String sentence = scanner.nextLine();
            if (!sentence.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(sentence);
                System.out.println("____________________________________________________________");
            } else {
                endConvo = true;
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
            }
        }
    }
}