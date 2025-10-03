package chara;

import java.util.List;
import java.util.Scanner;

import chara.task.Task;

/**
 * Handles all interactions with the user.
 * Reads commands from the user,
 * prints messages, task information, and error messages.
 * Provides helper methods to keep input/output logic separate
 * from the main program flow.
 */
public class Ui {
    private static final long LINE_LEN = 80;
    private final Scanner in = new Scanner(System.in);

    /**
     * Prints a horizontal line of underscores of length LINE_LEN, defined in Ui.java.
     */
    public void printLine() {
        for (long i = 0; i < LINE_LEN; i += 1) {
            System.out.print('_');
        }
        System.out.println();
    }


    /**
     * Prints the introduction message and character greeting at program start.
     */
    public void printIntro() {
        printLine();
        String logo = """
                 ____ _   _    _    ____   _   \s
                / ___| | | |  / \\  |  _ \\ / \\  \s
               | |   | |_| | / _ \\ | |_) / _ \\ \s
               | |___|  _  |/ ___ \\|  _ < ___ \\\s
                \\____|_| |_/_/   \\_\\_| \\_\\_/ \\_\\
               """;
        String charaSprite = """
                            .....=%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%=....            \s
                            .....=%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%=....            \s
                        ......#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#.....        \s
                        ..=+++%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%*++=.        \s
                   .......+%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%*.....    \s
                    ...%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%:.    \s
                .......%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%:.....\s
                .......%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%=..\s
                ...-***%%%%%%%%%%+------*%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%=..\s
                ...=%%%%%%%%%%%%%=......*%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%=..\s
                ...=%%%%%%%%%#.............:%%%-............:%%%%%%%%%%%%%%%%%=..\s
                ...=%%%%%%%%%#.............:%%%-............:%%#%%%%%%%%%%%%%%=...
                ...=%%%%%%%%%#.......%%%-.............%%%#......=%%%%%%%%%%%%%%%%%
                ===*%%%%%%*+++.......%%%-.............%%%#......=%%%%%%#+++#%%%%%%
                %%%%%%%%%%-..........%%%-.............%%%#......=%%%%%%*...#%%%%%%
                %%%%%%%%%%-..........%%%-.............%%%#......=%%#:......#%%%%%%
                %%%%%%%%%%-..........%%%-.............%%%#......=%%#:......#%%%%%%
                %%%%%%%%%%-............................................-%%%%%%%%%%
                %%%%%%%%%%-.........:......:........::..............:::-%%%%%%%%%%
                %%%%%%%%%%-...:::::::...*%%%......-%%%:..::::::::...#%%%%%%%%%%%%%
                %%%%%%%%%%####:::::::...::::%%%%%%*:::...:::::::+###%%%%%%%%%%%%%%
                %%%%%%%%%%%%%#:::::::......:%%%%%%*......:::::::+%%%%%%%%%%%%%%%%%
                %%%%%%%%%%%%%%%%%%%%%:...................-%%%%%%%%%%%%%%%%%%%%%%%%
                ***#%%#***#%%%%%%%%%%----------:......---=%%%%%%%%%%%%%#***#%%#***
                ...=%%#...+%%%%%%%%%%%%%%%%%%%%-......#%%%%%%%%%%%%%%%%*...#%%=...
                ............................%%%-......#%%*..........#%%*.........\s
                                        ....%%%-......#%%*..........#%%*..       \s""";
        System.out.println(logo);
        System.out.println(charaSprite);
        System.out.println("Chara: Greetings.");
        System.out.println("Chara: I am Chara! =)");
        printLine();
    }


    /**
     * Prints the outro message and character farewell at program end.
     */
    public void printOutro() {
        System.out.println("Chara: Let us erase this chat, and move on to the next =)");
        String heartSprite = """
    .#####.                  .*####.   \s
  -++#####++++=.        .=++++#####++- \s
..=###########*:...  ...:*###########=..
#################=.  .-#################
#################=.  .-#################
#################*++++*#################
########################################
########################################
########################################
########################################
########################################
########################################
########################################
*****##############################*****
    .##############################.   \s
    .##############################.   \s
    .+++++####################+++++.   \s
         .*###################.        \s
         .*###################.        \s
          .....*########*:.....        \s
              .*########*.             \s
              .*########*.             \s""";
        System.out.println(heartSprite);
        printLine();
    }

    /**
     * Reads and returns the next line of user input from standard input.
     *
     * @return the trimmed command string entered by the user
     */
    public String readCommand() {
        System.out.print("User: ");
        return in.nextLine().trim();
    }

    /**
     * Prints an error message prefixed with "Chara: " to the console.
     *
     * @param msg the error message to display
     */
    public void printError(String msg) {
        System.out.println("Chara: " + msg);
    }

    /**
     * Prints a message indicating that no save file could be found
     * and the program is starting with an empty task list.
     */
    public void printLoadingError() {
        System.out.println("Chara: A fresh start! (no save file yet).");
    }

    /**
     * Prints the current list of numbered tasks.
     * Shows a special message if the list is empty.
     *
     * @param tasks the TaskList containing all current tasks
     */
    public void printList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Chara: Your list is empty, silly!");
        } else {
            System.out.println("Chara: Here's your list! (What's it for anyway?)");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print((i + 1) + ". ");
                tasks.get(i).printTask();
                System.out.println();
            }
        }
    }

    /**
     * Prints a confirmation message after adding a new task to the list.
     *
     * @param t the task that was added
     * @param newSize the updated size of the task list
     */
    public void printAdded(Task t, int newSize) {
        System.out.println("Chara has added \""
                + t.getTaskTypeIcon() + t.getStatusIcon() + ' ' + t.getDescription()
                + "\" to your list.");
        System.out.println("Now you have " + newSize + " task(s) in your list! =)");
    }

    /**
     * Prints an error message when a user tries to access
     * a task index that does not exist in the list.
     */
    public void printTaskDoesNotExist() {
        System.out.println("Chara: That task doesn't exist...");
    }

    /**
     * Prints a confirmation message that the given task
     * has been marked as done.
     *
     * @param t the task that was marked as done
     */
    public void printMarked(Task t) {
        System.out.println("Chara: You never fail to impress!");
        System.out.print("Marked as done: ");
        t.printTask();
        System.out.println();
    }

    /**
     * Prints a confirmation message that the given task
     * has been unmarked (set to not done).
     *
     * @param t the task that was unmarked
     */
    public void printUnmarked(Task t) {
        System.out.println("Chara: This feels familiar.");
        System.out.print("Unmarked as done: ");
        t.printTask();
        System.out.println();
    }

    /**
     * Prints a confirmation message that a task was deleted,
     * shows the deleted task details, and displays the new task count.
     *
     * @param removed the task that was removed
     * @param newSize the updated number of tasks in the list
     */
    public void printDeleted(Task removed, int newSize) {
        if (removed.isDone()) {
            System.out.println("Chara: Done and dusted! =)");
        } else {
            System.out.println("Chara: Deleting it is kinda like doing it... right?");
        }
        System.out.print("Deleted task: ");
        removed.printTask();
        System.out.println();
        System.out.println("Now you have " + newSize + " task(s) in your list! =)");
    }

    /**
     * Prints the results of a "find" command, showing the tasks whose
     * descriptions matched the search keyword along with their original
     * 1-based indices in the task list.
     *
     * @param tasks           the full TaskList of tasks
     * @param matchingIndices the 1-based indices of matching tasks in the list
     */
    public void printFindResultsWithIndices(TaskList tasks, List<Integer> matchingIndices) {
        if (matchingIndices.isEmpty()) {
            System.out.println("Chara: I can't seem to find that...");
            return;
        }

        System.out.println("Chara: Here's what I found! =)");
        for (int index : matchingIndices) {
            System.out.print(index + ". ");
            tasks.get(index - 1).printTask();
            System.out.println();
        }
    }

}
