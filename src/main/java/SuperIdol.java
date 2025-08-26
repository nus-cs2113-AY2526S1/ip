import java.util.Scanner;

public class SuperIdol {

    private static Task[] toDoList = new Task[100];

    public static void main(String[] args) {
        String logo = " ____                       ___    _       _ \n"
                + "/ ___| _   _ _ __   ___ _ _|_ _|__| | ___ | |\n"
                + "\\___ \\| | | | '_ \\ / _ \\ '__| |/ _` |/ _ \\| |\n"
                + " ___) | |_| | |_) |  __/ |  | | (_| | (_) | |\n"
                + "|____/ \\__,_| .__/ \\___|_| |___\\__,_|\\___/|_|\n"
                + "            |_|                              \n";
        System.out.println("Hello! I'm SuperIdol\n" + logo);
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        while (true) {
            String command =  in.nextLine();
            if (command.equals("exit")) {
                talk("Bye. Hope to see you again soon!");
                System.exit(0);
            }
            else if (command.equals("list")) {
                showList();
            }
            else if (command.matches("^mark [0-9]+$") || command.matches("^unmark [0-9]+$")) {
                String[] commandPart = command.split(" ");
                int taskId = Integer.parseInt(commandPart[1]);
                if (commandPart[0].equals("mark")) {
                    mark(taskId);
                }
                else {
                    unmark(taskId);
                }
            }
            else {
                addToList(command);
            }
        }
    }

    public static void talk(String response) {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }

    public static void addToList(String command) {
        if (command.isBlank()) {
            return;
        }
        Task newTask = new Task(command);
        toDoList[Task.taskCount] = newTask;
        Task.taskCount++;
        talk("added: " + command);
    }

    public static void showList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < Task.taskCount; i++) {
            System.out.println((i+1) + ". " + toDoList[i].getTask());
        }
        System.out.println("____________________________________________________________");
    }

    public static void mark(int taskId) {
        if (taskId >= 1 && taskId <= Task.taskCount) {
            System.out.println("____________________________________________________________");
            toDoList[taskId - 1].mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(toDoList[taskId - 1].getTask());
            System.out.println("____________________________________________________________");
        }
    }

    public static void unmark(int taskId) {
        if (taskId >= 1 && taskId <= Task.taskCount) {
            System.out.println("____________________________________________________________");
            toDoList[taskId - 1].unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(toDoList[taskId - 1].getTask());
            System.out.println("____________________________________________________________");
        }
    }
}
