
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
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

        List<String> instructions = new ArrayList<>();

        System.out.println();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        boolean endConvo = false;
        while (!endConvo) {
            Scanner scanner = new Scanner(System.in);
            String sentence = scanner.nextLine();
            if (!sentence.equals("bye") && !sentence.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + sentence);
                instructions.add(sentence);
                System.out.println("____________________________________________________________");
            } else if (sentence.equals("list")) {
                System.out.println("____________________________________________________________");
                for  (int i = 0; i < instructions.size(); i++) {
                    System.out.println(i+1 + ". " + instructions.get(i));
                }
                System.out.println("____________________________________________________________");
            }
            else {
                endConvo = true;
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
            }
        }
    }
}