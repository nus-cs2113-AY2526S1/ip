package zoro.ui;

import java.util.List;
import zoro.model.Task;

/**
 * Handles all user interface output for the Zoro application.
 * Contains methods for displaying menus, instructions, and task information.
 */
public class UserInterface {


    /*    MENU    */
    /**
     * Prints a separator line for visual formatting.
     */
    public void printSeparator() {
        System.out.print("-----------------------\n\n");
    }

    /**
     * Prints the main menu introduction with Zoro logo and options.
     */
    public void printMenuIntro() {
        String ZORO_LOGO =
            "╔═════════════════════════════════════╗\n" +
            "║  ███████╗ ██████╗ ██████╗  ██████╗  ║\n" +
            "║  ╚══███╔╝██╔═══██╗██╔══██╗██╔═══██╗ ║\n" +
            "║    ███╔╝ ██║   ██║██████╔╝██║   ██║ ║\n" +
            "║   ███╔╝  ██║   ██║██╔══██╗██║   ██║ ║\n" +
            "║  ███████╗╚██████╔╝██║  ██║╚██████╔  ║\n" +
            "║  ╚══════╝ ╚═════╝ ╚═╝  ╚═╝ ╚═════╝  ║\n" +
            "╚═════════════════════════════════════╝";
        System.out.println(ZORO_LOGO);
        String name = "Roronoa Zoro";
        printSeparator();
        System.out.println("I'm " + name + " - somehow I ended up here, I was looking for soju..");
        System.out.println("What can I do for you?");
        System.out.println(
            "1: echo - I'll repeat what you say \n" +
            "2: task - Let's get your schedule sharper than my blades\n" +
            "More training exercises coming soon!\n" +
            "Type \"bye\" or \"exit\" to exit"
        );
        printSeparator();
    }


    /*    ECHO    */

    /**
     * Prints instructions for the echo mode.
     */
    public void printEchoInstruction() {
        System.out.println(
            "Echo training activated!\n" +
            "Type something and I will echo it!\n" +
            "Type \"bye\" when you're ready to end your training."
        );
        printSeparator();
    }

    /**
     * Prints the user's input message back to them.
     *
     * @param userInput the message to echo back
     */
    public void printEchoMessage(String userInput) {
        System.out.println(userInput);
    }


    /*    TASK    */

    /**
     * Prints instructions for task management mode.
     */
    public void printTaskInstruction() {
        System.out.println(
                "Listen up! Here's how you manage tasks like a proper swordsman:\n" +
                "a) todo [task] - Basic training, one task at a time\n" +
                "b) deadline [task] /by [deadline_day] - Time-limited challenges\n" +
                "c) event [event] /from [startTime] /to [endTime] - Scheduled duels and training\n" +
                "list - See all your current missions\n" +
                "mark [task_index] - Mark victories (or defeats)\n" +
                "delete [task_index] - Eradicate your enemies\n" +
                "find [keyword] - Locate targets\n" +
                "Now get to work!"
        );
        printSeparator();
    }
    /**
     * Prints the correct format for todo command.
     */
    public void printValidInputTodo() {
        System.out.println("Proper form: todo [task] - Like 'todo practice sword techniques'");
    }

    /**
     * Prints the correct format for mark command.
     */
    public void printValidInputMark() {
        System.out.println("Proper form: mark [task_number] - Pick the right target!");
    }

    /**
     * Prints the correct format for deadline command.
     */
    public void printValidInputDeadline() {
        System.out.println("Proper form: deadline [task] /by [deadline_day] - Set your training goals!");
    }

    /**
     * Prints the correct format for event command.
     */
    public void printValidInputEvent() {
        System.out.println("Proper form: event [event] /from [startTime] /to [endTime] - Schedule like a warrior!");
    }

    /**
     * Prints the correct format for delete command.
     */
    public void printValidInputDelete() {
        System.out.println("Proper form: delete [task_number]");
    }

    /**
     * Prints the correct format for find command.
     */
    public void printValidInputFind() {
        System.out.println("Proper form: find [keyword]");
    }

    /**
     * Prints confirmation message when a task is marked as done.
     *
     * @param userInput - the task description that was marked
     */
    public void printTaskMarked(String userInput) {
        System.out.println("Task " + userInput + " has been conquered. Time for the next challenge!");
    }

    /**
     * Prints confirmation message when a new task is added.
     *
     * @param task - the task that was added
     */
   public void printTaskAdded(Task task) {
        System.out.println("New mission accepted: " + task.toString());
        System.out.println("Added to your training regimen.");
   }

    /**
     * Prints confirmation message when a task is unmarked (marked as not done).
     *
     * @param userInput - the task description that was unmarked
     */
    public void printTaskUnmarked(String userInput) {
        System.out.println("Task " + userInput + " is back in action. Sometimes you gotta fight the same battle twice.");
    }

    /**
     * Prints confirmation message when a task is deleted.
     *
     * @param task - the task that was deleted
     */
    public void printTaskDeleted(Task task) {
        System.out.println("Target: " + task.toString() + " has been eliminated");
    }

    /**
     * Prints the list of tasks found by a search.
     *
     * @param tasks - the list of tasks that matched the search criteria
     */
    public void printTasksFound(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            System.out.println("I got pretty lost looking for these:");
            for (Task task : tasks) {
                System.out.println(task.toString());
            }
        } else {
            System.out.println("I think I got lost, I couldn't find anything!");
        }
    }

    /**
     * Prints the complete list of all tasks.
     *
     * @param tasks - the list of all current tasks
     */
    public void printTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks? Are you staying on top of your work or are you slacking off?");
            System.out.println("If you want to become the greatest swordsman theres no time for slacking - get to work.");
        }

        System.out.println("Here's your current training schedule: ");
        for (int i=0; i<tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).toString());
        }
    }

    /*    GENERAL    */

    /**
     * Prints goodbye message when exiting the application.
     */
    public void printGoodbye() {
        System.out.println("See ya, don't get lost!");
    }

    /**
     * Prints error message for invalid user input.
     */
    public void printInvalidInput() {
        System.out.println("That's not a valid input, you're really starting to act like that stupid chef.");
    }

    /**
     * Prints validation error messages with Zoro-themed formatting.
     *
     * @param errorMessage - the specific error message to display
     */
    public void printValidationError(String errorMessage) {
        System.out.println("Hold on, whats this?");
        System.out.println(errorMessage);
        System.out.println("Fix your form and try again!");
    }

}
