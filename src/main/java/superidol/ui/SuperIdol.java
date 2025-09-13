package superidol.ui;

import superidol.task.Deadline;
import superidol.task.Event;
import superidol.task.Task;
import superidol.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuperIdol {

    private static ArrayList<Task> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        greeting();

        Scanner in = new Scanner(System.in);


        while (true) {
            String command =  in.nextLine().trim();
            try {
                String commandKeyWord = command.split(" ")[0];
                switch (commandKeyWord) {
                case "exit":
                    exit();
                    break;
                case "list":
                    showList();
                    break;
                case "mark":
                    mark(command, true);
                    break;
                case "unmark":
                    mark(command, false);
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
                case "delete":
                    deleteTask(command);
                    break;
                default:
                    reponse();
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                reponse();
            }
        }
    }

    public static void reponse() {
        talk("OOPS!!! I'm sorry, but I don't know what that means :-(\n"
            + "try: todo <task>\n"
            + "try: deadline <task> /by <time>\n"
            + "try: event <task> /from <start> /to <end>");
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
        } else {
            talk("Wrong input! Try \"deadline <task> /by <time>\"");
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
        } else {
            talk("Wrong input! Try \"event <task> /from <start> /to <end>\"");
        }
    }

    public static void addTodo(String command) {
        // remove "todo"
        String task = command.substring(4).trim();
        if (task.isBlank()) {
            talk("Wrong input! Try \"todo <task>\"");
        } else {
            Todo todo = new Todo(task);
            addToList(todo, task);
        }
    }

    public static void addToList(Task newTask, String task) {
        // add task to the list
        toDoList.add(newTask);
        Task.taskCount++;

        // print the result
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.getTask());
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void showList() {
        if (Task.taskCount == 0) {
            talk("There are no tasks in the list.\n" + "Try add some.");
        } else {
            System.out.println("____________________________________________________________");
            for (int i = 0; i < Task.taskCount; i++) {
                System.out.println((i + 1) + ". " + toDoList.get(i).getTask());
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void mark(String command, boolean isDone) {
        try {
            int taskId = Integer.parseInt(command.split(" ")[1]);
            if (taskId >= 1 && taskId <= Task.taskCount) {
                Task targetTask = toDoList.get(taskId - 1);
                targetTask.setIsDone(isDone);
                if (isDone) {
                    talk("Nice! I've marked this task as done:\n"
                            + targetTask.getTask());
                } else {
                    talk("OK, I've marked this task as not done yet:\n"
                            + targetTask.getTask());
                }
            } else {
                talk("You only have " + Task.taskCount + " task(s) in the list.");
            }
        } catch (NumberFormatException e) {
            talk("Wrong input! Try \"(un)mark <task_index>\"");
        }
    }

    public static void deleteTask(String command) {
        try {
            int taskId = Integer.parseInt(command.split(" ")[1]);
            if (taskId >= 1 && taskId <= Task.taskCount) {
                talk("Noted. I've removed this task:\n"
                        + toDoList.get(taskId - 1).getTask()
                        + "\nNow you have " + (Task.taskCount - 1) + " tasks in the list.");
                toDoList.remove(taskId - 1);
                Task.taskCount--;
            } else {
                talk("You only have " + Task.taskCount + " task(s) in the list.");
            }
        } catch (NumberFormatException e) {
            talk("Wrong input! Try \"delete <task_index>\"");
        }
    }
}
