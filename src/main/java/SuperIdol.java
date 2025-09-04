import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuperIdol {

    private static Task[] toDoList = new Task[100];

    public static void main(String[] args) {
        greeting();

        Scanner in = new Scanner(System.in);


        while (true) {
            String command =  in.nextLine();
            String commandKeyWord = command.split(" ")[0];
            switch (commandKeyWord) {
            case "exit":
                exit();
                break;
            case "list":
                showList();
                break;
            case "mark":
                mark(command);
                break;
            case "unmark":
                unmark(command);
                break;
            case "todo":
                addTodo(command);
                break;
            case "deadline":
                addDeadline(command);
                break;
            case "event":
                addEvent(command);
                break;
            default:
                addTask(command);
                break;
            }
        }
    }

    public static void greeting() {
        String logo = " ____                       ___    _       _\n"
                + "/ ___| _   _ _ __   ___ _ _|_ _|__| | ___ | |\n"
                + "\\___ \\| | | | '_ \\ / _ \\ '__| |/ _` |/ _ \\| |\n"
                + " ___) | |_| | |_) |  __/ |  | | (_| | (_) | |\n"
                + "|____/ \\__,_| .__/ \\___|_| |___\\__,_|\\___/|_|\n"
                + "            |_|\n";
        System.out.println("Hello! I'm SuperIdol\n" + logo);
        System.out.println("What can I do for you?");
    }

    public static void exit() {
        talk("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public static void talk(String response) {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }

    public static void addDeadline(String command) {
        Pattern pattern = Pattern.compile("(deadline)\\s(.+?)\\s/by\\s(.+)");
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            String deadline = matcher.group(2).trim();
            String time = matcher.group(3).trim();

            Deadline newTask = new Deadline(deadline, time);
            addToList(newTask, deadline);
        }
        else {
            talk("Wrong input! \"deadline <task> /by <time>\"");
        }
    }

    public static void addEvent(String command) {
        Pattern pattern = Pattern.compile("(event)\\s(.+?)\\s/from(.+?)\\s/to(.+)");
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            String task = matcher.group(2).trim();
            String startTime = matcher.group(3).trim();
            String endTime = matcher.group(4).trim();

            Event event = new Event(task, startTime, endTime);
            addToList(event, task);
        }
        else {
            talk("Wrong input! \"event <task> /from <start> /to <end>\"");
        }


    }

    public static void addTodo(String command) {
        // remove "todo"
        String task = command.substring(4).trim();
        Todo todo = new Todo(task);
        addToList(todo, task);
    }

    public static void addTask(String command) {
        Task newTask = new Task(command);
        addToList(newTask, command);
    }

    public static void addToList(Task newTask, String task) {
        // add task to the list
        toDoList[Task.taskCount] = newTask;
        Task.taskCount++;

        // print the result
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(toDoList[Task.taskCount - 1].getTask());
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void showList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < Task.taskCount; i++) {
            System.out.println((i + 1) + ". " + toDoList[i].getTask());
        }
        System.out.println("____________________________________________________________");
    }

    public static void mark(String command) {
        int taskId = Integer.parseInt(command.split(" ")[1]);
        if (taskId >= 1 && taskId <= Task.taskCount) {
            toDoList[taskId - 1].mark();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(toDoList[taskId - 1].getTask());
            System.out.println("____________________________________________________________");
        }
    }

    public static void unmark(String command) {
        int taskId = Integer.parseInt(command.split(" ")[1]);
        if (taskId >= 1 && taskId <= Task.taskCount) {
            toDoList[taskId - 1].unmark();
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(toDoList[taskId - 1].getTask());
            System.out.println("____________________________________________________________");
        }
    }
}
