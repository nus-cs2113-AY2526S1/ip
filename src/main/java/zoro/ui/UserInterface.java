package zoro.ui;

import java.util.List;
import zoro.model.Task;

public class UserInterface {


    /*    MENU    */

    public void printSeparator() {
        System.out.print("-----------------------\n\n");
    }

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


    public void printEchoInstruction() {
        System.out.println(
            "Echo training activated!\n" +
            "Type something and I will echo it!\n" +
            "Type \"bye\" when you're ready to end your training."
        );
        printSeparator();
    }

    public void printEchoMessage(String userInput) {
        System.out.println(userInput);
    }


    /*    TASK    */


    public void printTaskInstruction() {
        System.out.println(
                "Listen up! Here's how you manage tasks like a proper swordsman:\n" +
                "a) todo [task] - Basic training, one task at a time\n" +
                "b) deadline [task] /by [deadline_day] - Time-limited challenges\n" +
                "c) event [event] /from [startTime] /to [endTime] - Scheduled duels and training\n" +
                "list - See all your current missions\n" +
                "mark [task_index] - Mark victories (or defeats)\n" +
                "Now get to work!"
        );
        printSeparator();
    }

    public void printValidInputTodo() {
        System.out.println("Proper form: todo [task] - Like 'todo practice sword techniques'");
    }

    public void printValidInputMark() {
        System.out.println("Proper form: mark [task_number] - Pick the right target!");
    }

    public void printValidInputDeadline() {
        System.out.println("Proper form: deadline [task] /by [deadline_day] - Set your training goals!");
    }

    public void printValidInputEvent() {
        System.out.println("Proper form: event [event] /from [startTime] /to [endTime] - Schedule like a warrior!");
    }

    public void printValidInputDelete() {
        System.out.println("Proper form: delete [task_number]");
    }

    public void printTaskMarked(String userInput) {
        System.out.println("Task " + userInput + " has been conquered. Time for the next challenge!");
    }

   public void printTaskAdded(Task task) {
        System.out.println("New mission accepted: " + task.toString());
        System.out.println("Added to your training regimen.");
   }

    public void printTaskUnmarked(String userInput) {
        System.out.println("Task " + userInput + " is back in action. Sometimes you gotta fight the same battle twice.");
    }

    public void printTaskDeleted(Task task) {
        System.out.println("Target: " + task.toString() + " has been eliminated");
    }

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


    public void printGoodbye() {
        System.out.println("See ya, don't get lost!");
    }

    public void printInvalidInput() {
        System.out.println("That's not a valid input, you're really starting to act like that stupid chef.");
    }

    public void printValidationError(String errorMessage) {
        System.out.println("Hold on, whats this?");
        System.out.println(errorMessage);
        System.out.println("Fix your form and try again!");
    }

}
