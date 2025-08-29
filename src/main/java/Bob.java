import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
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

        ArrayList<Task> instructions = new ArrayList<>();

        System.out.println();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        boolean endConvo = false;
        while (!endConvo) {
            Scanner scanner = new Scanner(System.in);
            String sentence = scanner.nextLine();
            String[] sentenceArray = sentence.split("\\s+");

            if (sentence.equals("bye")) {
                endConvo = true;
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
            }
            if (Objects.equals(sentenceArray[0], "mark")) {
                System.out.println("______________________________________________________________");
                int index = Integer.parseInt(sentenceArray[1]) - 1;
                instructions.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done");
                System.out.println("  [" + instructions.get(index).getStatusIcon() + "] " + instructions.get(index).description);
                System.out.println("____________________________________________________________");

            } else if (Objects.equals(sentenceArray[0], "unmark")) {
                int index = Integer.parseInt(sentenceArray[1]) - 1;
                instructions.get(index).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet");
                System.out.println("  [" + instructions.get(index).getStatusIcon() + "] " + instructions.get(index).description);
                System.out.println("____________________________________________________________");

            } else if (!sentence.equals("bye") && !sentence.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + sentence);
                Task t = new Task(sentence);
                instructions.add(t);
                System.out.println("____________________________________________________________");
            } else if (sentence.equals("list")) {
                System.out.println("____________________________________________________________");
                for  (int i = 0; i < instructions.size(); i++) {
                    System.out.println(i+1 + ". [" + instructions.get(i).getStatusIcon() + "] " + instructions.get(i).description);
                }
                System.out.println("____________________________________________________________");
            }
        }
    }
}