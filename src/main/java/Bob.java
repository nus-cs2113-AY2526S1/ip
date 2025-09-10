import java.awt.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
public class Bob {

    public static void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }

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
        printLineSeparator();
        boolean endConvo = false;
        Scanner scanner = new Scanner(System.in);
        while (!endConvo) {
            //Scanner scanner = new Scanner(System.in);
            String sentence = scanner.nextLine();
            String[] sentenceArray = sentence.split("\\s+");

            try {
                // User says bye
                if (sentence.equals("bye")) {
                    printLineSeparator();
                    endConvo = true;
                    System.out.println("Bye. Hope to see you again soon!");
                }

                // To-do Tasks
                else if (Objects.equals(sentenceArray[0], "todo")) {
                    try {
                        if (sentenceArray.length < 2 || sentenceArray[1].isEmpty()) {
                            throw new BobException("Description of a todo cannot be empty - Bob 😡");
                        }
                        printLineSeparator();
                        int firstSpaceIndex = sentence.indexOf(" ");
                        String todoTask = sentence.substring(firstSpaceIndex + 1);
                        Todo t = new Todo(todoTask);
                        System.out.println("Got it. I've added this task");
                        instructions.add(t);
                        System.out.println("  " + t);
                        System.out.println("Now you have " + instructions.size() + " tasks in the list");
                    } catch (BobException e) {
                        System.out.println(e.getMessage());
                    }

                    // Deadline Tasks
                } else if (Objects.equals(sentenceArray[0], "deadline")) {
                    try {
                        printLineSeparator();
                        Deadline d = Deadline.getDeadline(sentenceArray, sentence);
                        System.out.println("Got it. I've added this task");
                        instructions.add(d);
                        System.out.println(" " + d);
                        System.out.println("Now you have " + instructions.size() + " tasks in the list");
                    } catch (BobException e) {
                        System.out.println(e.getMessage());
                    }

                    // Event Tasks
                } else if (Objects.equals(sentenceArray[0], "event")) {
                    try {
                        printLineSeparator();
                        Event e = Event.getEvent(sentenceArray, sentence);
                        System.out.println("Got it. I've added this task");
                        instructions.add(e);
                        System.out.println("  " + e);
                        System.out.println("Now you have " + instructions.size() + " tasks in the list");
                    } catch (BobException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // Mark and unmark Tasks
                else if (Objects.equals(sentenceArray[0], "mark")) {
                    printLineSeparator();
                    int index = Integer.parseInt(sentenceArray[1]) - 1;
                    instructions.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done");
                    System.out.println("  [" + instructions.get(index).getStatusIcon() + "] " + instructions.get(index).description);
                } else if (Objects.equals(sentenceArray[0], "unmark")) {
                    printLineSeparator();
                    int index = Integer.parseInt(sentenceArray[1]) - 1;
                    instructions.get(index).markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet");
                    System.out.println("  [" + instructions.get(index).getStatusIcon() + "] " + instructions.get(index).description);

                    // Show List of tasks
                } else if (sentence.equals("list")) {
                    printLineSeparator();
                    for (int i = 0; i < instructions.size(); i++) {
                        System.out.println((i + 1) + ". " + instructions.get(i));
                    }
                } else {
                    throw new BobException("Sorry, I don't know what that means - Bob 🤔");
                }

                printLineSeparator();
            }

            // Normal Tasks
            catch (BobException e){
                printLineSeparator();
                System.out.println(" "+e.getMessage());
                printLineSeparator();
            }
        }
    }
}
