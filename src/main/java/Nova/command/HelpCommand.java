package Nova.command;

import Nova.storage.Storage;
import Nova.task.TaskList;
import Nova.ui.TextUi;

/**
 * Represents a command that displays a list of available commands
 * and their usage to the user.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command by printing all available commands
     * and their descriptions to the console.
     *
     * @param tasks   The TaskList containing all current tasks (not used here).
     * @param ui      The TextUi instance for interacting with the user.
     * @param storage The Storage instance for saving/loading tasks (not used here).
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showLineSeparator();
        System.out.println(" Here are the available commands:");
        System.out.println("   list                - Show all tasks");
        System.out.println("   todo <desc>         - Add a todo");
        System.out.println("   deadline <desc> /by <DD/MM/YYYY HHmm> - Add a deadline");
        System.out.println("   event <desc> /from <DD/MM/YYYY HHmm> /to <DD/MM/YYYY HHmm> - Add an event");
        System.out.println("   mark <num>          - Mark a task as done");
        System.out.println("   unmark <num>        - Mark a task as not done");
        System.out.println("   delete <num>        - Delete a task");
        System.out.println("   find <desc>         - Find a task by partial or full description");
        System.out.println("   help                - Show this help message");
        System.out.println("   bye                 - Exit the program");
        ui.showLineSeparator();
    }
}
