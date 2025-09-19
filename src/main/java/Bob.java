import java.awt.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class Bob {

    static final String FILE_PATH = "./data/bob.txt";

    public static void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                File parentDir = file.getParentFile();
                if (!parentDir.exists() && !parentDir.mkdirs()) {
                    System.out.println("Warning: could not create directory " + parentDir.getAbsolutePath());
                }
                if (!file.createNewFile()) {
                    System.out.println("Warning: could not create file " + file.getAbsolutePath());
                }
                return tasks;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];

                switch (type) {
                case "T": {
                    Todo t = new Todo(desc);
                    if (isDone) t.markAsDone();
                    tasks.add(t);
                    break;
                }
                case "D": {
                    Deadline d = new Deadline(desc, parts[3]);
                    if (isDone) d.markAsDone();
                    tasks.add(d);
                    break;
                }
                case "E": {
                    Event e = new Event(desc, parts[3], parts[4]);
                    if (isDone) e.markAsDone();
                    tasks.add(e);
                    break;
                }
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                System.out.println("Warning: could not create directory " + parentDir.getAbsolutePath());
            }

            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    fw.write("T | " + (task.isDone ? "1" : "0")
                            + " | " + task.description + System.lineSeparator());
                } else if (task instanceof Deadline d) {
                    fw.write("D | " + (d.isDone ? "1" : "0")
                            + " | " + d.description + " | " + d.by + System.lineSeparator());
                } else if (task instanceof Event e) {
                    fw.write("E | " + (e.isDone ? "1" : "0")
                            + " | " + e.description + " | " + e.from + " | " + e.to + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
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

        ArrayList<Task> instructions = loadTasks();

        System.out.println();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        printLineSeparator();
        boolean endConvo = false;
        Scanner scanner = new Scanner(System.in);
        while (!endConvo) {
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
                        saveTasks(instructions);
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
                        saveTasks(instructions);
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
                        saveTasks(instructions);
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
                    saveTasks(instructions);
                } else if (Objects.equals(sentenceArray[0], "unmark")) {
                    printLineSeparator();
                    int index = Integer.parseInt(sentenceArray[1]) - 1;
                    instructions.get(index).markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet");
                    System.out.println("  [" + instructions.get(index).getStatusIcon() + "] " + instructions.get(index).description);
                    saveTasks(instructions);

                } else if (Objects.equals(sentenceArray[0], "delete")) {
                    int indexToRemove = Integer.parseInt(sentenceArray[1]) - 1;
                    if (indexToRemove < 0 || indexToRemove >= instructions.size()) {
                        throw new BobException("Sorry, please list a number within the list");
                    }
                    printLineSeparator();
                    System.out.println("Bob has removed this task for you!");
                    System.out.println(instructions.get(indexToRemove).description);
                    instructions.remove(indexToRemove);
                    System.out.println("You have "+instructions.size()+" tasks in the list");
                }
                // Show List of tasks
                else if (sentence.equals("list")) {
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
