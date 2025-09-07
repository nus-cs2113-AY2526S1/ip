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

            if (sentence.equals("bye")) {
                endConvo = true;
                printLineSeparator();
                System.out.println("Bye. Hope to see you again soon!");
            }
            if (Objects.equals(sentenceArray[0], "todo")) {
                printLineSeparator();
                int firstSpaceIndex = sentence.indexOf(" ");
                String todoTask = sentence.substring(firstSpaceIndex+1);
                Todo t = new Todo(todoTask);
                System.out.println("Got it. I've added this task");
                instructions.add(t);
                System.out.println("  " + t);
                System.out.println("Now you have " + instructions.size() +  " tasks in the list");
            } else if (Objects.equals(sentenceArray[0], "deadline")) {
                int firstSpaceIndex = sentence.indexOf(" ");
                int byIndex = sentence.indexOf("/by");
                String deadlineTask = sentence.substring(firstSpaceIndex+1, byIndex).trim();
                String by = sentence.substring(byIndex+3).trim();
                Deadline d = new Deadline(deadlineTask, by);
                instructions.add(d);
                System.out.println(" " + d);
                System.out.println("Now you have " + instructions.size() +  " tasks in the list");
            } else if (Objects.equals(sentenceArray[0], "event")) {
                int firstSpaceIndex = sentence.indexOf(" ");
                int fromIndex = sentence.indexOf("/from");
                int toIndex = sentence.indexOf("/to");

                String eventTask = sentence.substring(firstSpaceIndex+1, fromIndex).trim();
                String from = sentence.substring(fromIndex+5, toIndex).trim();
                String to = sentence.substring(toIndex+3).trim();

                Event e = new Event(eventTask, from, to);
                instructions.add(e);
                System.out.println("  " + e);
                System.out.println("Now you have " + instructions.size()    +  " tasks in the list");
            }
            else if (Objects.equals(sentenceArray[0], "mark")) {
                printLineSeparator();
                int index = Integer.parseInt(sentenceArray[1]) - 1;
                instructions.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done");
                System.out.println("  [" + instructions.get(index).getStatusIcon() + "] " + instructions.get(index).description);
            } else if (Objects.equals(sentenceArray[0], "unmark")) {
                int index = Integer.parseInt(sentenceArray[1]) - 1;
                instructions.get(index).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet");
                System.out.println("  [" + instructions.get(index).getStatusIcon() + "] " + instructions.get(index).description);
            } else if (!sentence.equals("bye") && !sentence.equals("list")) {
                printLineSeparator();
                System.out.println("added: " + sentence);
                Task t = new Task(sentence);
                instructions.add(t);
                System.out.println("Now you have " + instructions.size()    +  " tasks in the list");
            } else if (sentence.equals("list")) {
                printLineSeparator();
                for  (int i = 0; i < instructions.size(); i++) {
                    System.out.println((i + 1) + ". " + instructions.get(i));
                    }
                }
                printLineSeparator();
            }
        }
    }
