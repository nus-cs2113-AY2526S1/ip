package Nova.command;

import Nova.storage.Storage;
import Nova.task.TaskList;
import Nova.ui.TextUi;

public class HelpCommand extends Command {
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
        System.out.println("   help                - Show this help message");
        System.out.println("   bye                 - Exit the program");
        ui.showLineSeparator();
    }
}
