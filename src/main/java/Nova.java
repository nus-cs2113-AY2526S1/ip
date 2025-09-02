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
            } else if (userInput.startsWith("todo")) {
                String taskDescription = userInput.substring(5);
                Task newTask = new Todo(taskDescription);
                tasks[taskCount++] = newTask;
                printTaskAdded(newTask, taskCount);
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split("/by");
                String taskDescription = parts[0].substring(9).trim();
                String by = parts[1].trim();
                Task newTask = new Deadline(taskDescription, by);
                tasks[taskCount++] = newTask;
                printTaskAdded(newTask, taskCount);
            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.split("/from|/to");
                String taskDescription = parts[0].substring(6).trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                Task newTask = new Event(taskDescription, from, to);
                tasks[taskCount++] = newTask;
                printTaskAdded(newTask, taskCount);
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

    public static void printTaskAdded(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list");
        System.out.println("____________________________________________________________");
    }
}

