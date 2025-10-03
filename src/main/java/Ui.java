import java.util.ArrayList;

/**
 * Handles all CLI interactions with the user.
 * Provides methods to display messages, errors, task lists, and task updates.
 */
public class Ui {
    
    public void showHelp() {
    sugonPrint(
        "Available commands:",
        "  list                        - Show all tasks",
        "  todo <desc>                - Add a ToDo task",
        "  deadline <desc> /by <date> - Add a Deadline",
        "  event <desc> /from <start> /to <end> - Add an Event",
        "  mark <num>                 - Mark a task as done",
        "  unmark <num>               - Unmark a task",
        "  delete <num>               - Delete a task",
        "  bye                        - Exit program",
        "  help                       - Show command message"
    );
}

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    private static void sugonPrint(String... messages) {
        System.out.println("____________________________________________________________");
        for (String msg : messages) {
            System.out.println(msg);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errors One or more error messages to display.
     */
    public void showError(String... errors) {
        StringBuilder sb = new StringBuilder();
        for (String error : errors) {
            sb.append(error).append(" ");
        }
        sugonPrint("Error: " + sb.toString().trim());
    }

    public void showWelcome() {
        sugonPrint("Hello! I'm Sugon", "What can I do for you?");
    }

    public void showGoodbye() {
        sugonPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the full list of tasks.
     *
     * @param taskList The list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> taskList) {
        showLine();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
        showLine();
    }

    /**
     * Displays a message that a task has been added.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks after addition.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + 
            (totalTasks == 1 ? " task" : " tasks") + " in the list.");
        showLine();
    }

    /**
     * Displays a message that a task has been removed.
     *
     * @param task The task that was removed.
     * @param totalTasks The total number of tasks remaining.
     */
    public void showTaskRemoved(Task task, int totalTasks) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + 
            (totalTasks == 1 ? " task" : " tasks") + " in the list.");
        showLine();
    }

}
