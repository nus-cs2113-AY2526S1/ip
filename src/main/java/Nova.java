import java.util.Scanner;

public class Nova {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;

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

            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i].toString());
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                Task t = tasks[taskNumber - 1];
                t.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("    " + t);
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                Task t = tasks[taskNumber - 1];
                t.markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've maked this task as not done yet:");
                System.out.println("    " + t);
                System.out.println("____________________________________________________________");
            } else {
                Task newTask = new Task(userInput);
                tasks[taskCount] = newTask;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + userInput);
                System.out.println("___________________________________________________________");
            }
        }
    }
}

