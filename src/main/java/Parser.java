import java.util.Objects;

/**
 * Parses input commands and dispatches actions to the model
 */
public class Parser {

    /** Prints a horizontal line to the console as design feature */
    public void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Parses a user command string and executes the corresponding action.
     *
     * @param sentence the input entered by the user.
     * @param bob the current {@link Bob} instance, used to update conversation state.
     */
    public void parsing(String sentence, Bob bob) {
        String[] sentenceArray = sentence.split("\\s+");
        Storage storage = new Storage("data/bob.txt");
        TaskList instructions = new TaskList(storage.loadTasks());

        try {
            if (Objects.equals(sentenceArray[0], "bye")) {
                printLineSeparator();
                bob.endConvo = true;
                System.out.println("Bye. Hope to see you again soon!");
                printLineSeparator();
            } else if (Objects.equals(sentenceArray[0], "todo")) {
                try {
                    if (sentenceArray.length < 2 || sentenceArray[1].isEmpty()) {
                        throw new BobException("Description of a todo cannot be empty - Bob ðŸ˜¡");
                    }
                    printLineSeparator();
                    int firstSpaceIndex = sentence.indexOf(" ");
                    String todoTask = sentence.substring(firstSpaceIndex + 1);
                    Todo t = new Todo(todoTask);
                    System.out.println("Got it. I've added this task");
                    instructions.add(t);
                    System.out.println("  " + t);
                    System.out.println("Now you have " + instructions.size() + " tasks in the list");
                    storage.saveTasks(instructions.getAll());
                    printLineSeparator();
                } catch (BobException e) {
                    printLineSeparator();
                    System.out.println(e.getMessage());
                    printLineSeparator();
                }
            } else if (Objects.equals(sentenceArray[0], "deadline")) {
                try {
                    printLineSeparator();
                    Deadline d = Deadline.getDeadline(sentenceArray, sentence);
                    System.out.println("Got it. I've added this task");
                    instructions.add(d);
                    System.out.println("  " + d);
                    System.out.println("Now you have " + instructions.size() + " tasks in the list");
                    storage.saveTasks(instructions.getAll());
                    printLineSeparator();
                } catch (BobException e) {
                    printLineSeparator();
                    System.out.println(e.getMessage());
                    printLineSeparator();
                }
            } else if (Objects.equals(sentenceArray[0], "event")) {
                try {
                    printLineSeparator();
                    Event ev = Event.getEvent(sentenceArray, sentence);
                    System.out.println("Got it. I've added this task");
                    instructions.add(ev);
                    System.out.println("  " + ev);
                    System.out.println("Now you have " + instructions.size() + " tasks in the list");
                    storage.saveTasks(instructions.getAll());
                    printLineSeparator();
                } catch (BobException e) {
                    printLineSeparator();
                    System.out.println(e.getMessage());
                    printLineSeparator();
                }
            } else if (Objects.equals(sentenceArray[0], "mark")) {
                printLineSeparator();
                int index = Integer.parseInt(sentenceArray[1]) - 1;
                instructions.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done");
                System.out.println("  [" + instructions.get(index).getStatusIcon() + "] " + instructions.get(index).description);
                storage.saveTasks(instructions.getAll());
                printLineSeparator();
            } else if (Objects.equals(sentenceArray[0], "unmark")) {
                printLineSeparator();
                int index = Integer.parseInt(sentenceArray[1]) - 1;
                instructions.get(index).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet");
                System.out.println("  [" + instructions.get(index).getStatusIcon() + "] " + instructions.get(index).description);
                storage.saveTasks(instructions.getAll());
                printLineSeparator();
            } else if (Objects.equals(sentenceArray[0], "delete")) {
                int indexToRemove = Integer.parseInt(sentenceArray[1]) - 1;
                if (indexToRemove < 0 || indexToRemove >= instructions.size()) {
                    throw new BobException("Sorry, please list a number within the list");
                }
                printLineSeparator();
                System.out.println("Bob has removed this task for you!");
                System.out.println(instructions.get(indexToRemove).description);
                instructions.remove(indexToRemove);
                System.out.println("You have " + instructions.size() + " tasks in the list");
                storage.saveTasks(instructions.getAll());
                printLineSeparator();
            } else if (Objects.equals(sentenceArray[0], "find")) {
                if (sentenceArray.length < 2) {
                    throw new BobException("Please provide a keyword to find - Bob");
                }
                printLineSeparator();
                int firstSpaceIndex = sentence.indexOf(' ');
                String keyword = sentence.substring(firstSpaceIndex + 1).trim();
                if (keyword.isEmpty()) {
                    throw new BobException("Your find keyword cannot be empty - Bob ðŸ˜¡");
                }
                System.out.println("Here are the matching tasks in your list:");
                int shown = 0;
                String query = keyword.toLowerCase();
                for (int i = 0; i < instructions.size(); i++) {
                    Task t = instructions.get(i);
                    if (t.description.toLowerCase().contains(query)) {
                        shown++;
                        System.out.println(shown + ". " + t);
                    }
                }
                if (shown == 0) {
                    System.out.println("(none)");
                }
                printLineSeparator();
            } else if (sentence.equals("list")) {
                printLineSeparator();
                for (int i = 0; i < instructions.size(); i++) {
                    System.out.println((i + 1) + ". " + instructions.get(i));
                }
                printLineSeparator();
            } else {
                throw new BobException("Sorry, I don't know what that means - Bob ðŸ¤”");
            }
        } catch (BobException e) {
            printLineSeparator();
            System.out.println(" " + e.getMessage());
            printLineSeparator();
        }
    }
}