import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles list of logic commands for each task within Tasklist class.
 * Provides commands to bye, list, mark, unmark, todo, deadline, event, delete, find and help.
 */
public class Parser {
    /**
     * Executes a given command with arguments.
     *
     * @param command The command keyword (e.g., "todo", "mark").
     * @param args The arguments following the command (task description, index, etc.).
     * @param tasks The Tasklist object containing the tasks.
     * @param ui The Ui object for displaying messages to the user.
     * @param storage The Storage object for saving tasks to file.
     * @return true if the program should continue running, false if the command is "bye".
     */
    public static boolean executeCommand(String command, String args, Tasklist tasks, Ui ui, Storage storage) {
        switch (command) {
        case "bye":
            ui.showGoodbye();
            return false; // signal to exit

        case "list":
            ui.showTaskList(tasks.getTasks());
            break;
        
        case "help":
            ui.showHelp();
            break;

        case "mark":
            try {
                int idx = Integer.parseInt(args) - 1;
                tasks.markTask(idx);
                storage.saveTasks(tasks.getTasks());
                ui.showTaskList(tasks.getTasks());
            } catch (Exception e) {
                ui.showError("Invalid task number.");
            }
            break;

        case "unmark":
            try {
                int idx = Integer.parseInt(args) - 1;
                tasks.unmarkTask(idx);
                storage.saveTasks(tasks.getTasks());
                ui.showTaskList(tasks.getTasks());
            } catch (Exception e) {
                ui.showError("Invalid task number.");
            }
            break;

        case "todo":
            if (args == null || args.trim().isEmpty()) {
                ui.showError("Description cannot be empty.");
                break;
            }
            Task toDo_Task = new ToDo(args);
            tasks.addTask(toDo_Task);
            try {
                storage.saveTasks(tasks.getTasks());
            } catch (IOException e) {
                ui.showError("Failed to save tasks.");
            }
            ui.showTaskAdded(toDo_Task, tasks.getTasks().size());
            break;

        case "deadline":
            try {
                String[] dParts = args.split("/by", 2);
                if (dParts.length < 2 || dParts[0].trim().isEmpty() || dParts[1].trim().isEmpty()) {
                    ui.showError("Invalid deadline format. Use: deadline <desc> /by <date>");
                    break;
                }
                String desc = dParts[0].trim();
                String deadlineDate = dParts[1].trim();
                Task deadline_Task = new Deadline(desc, deadlineDate);
                tasks.addTask(deadline_Task);
                storage.saveTasks(tasks.getTasks());
                ui.showTaskAdded(deadline_Task, tasks.getTasks().size());
            } catch (Exception e) {
                ui.showError("Invalid deadline format. Use: deadline <desc> /by <date>");
            }
            break;

        case "event":
            try {
                String[] eParts = args.split("/from", 2); 
                if (eParts.length < 2 || eParts[0].trim().isEmpty() || eParts[1].trim().isEmpty()) {
                    ui.showError("Invalid event format. Use: event <desc> /from <start> /to <end>");
                    break;
                }
                String desc = eParts[0].trim();
                String[] times = eParts[1].split("/to", 2);
                if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                    ui.showError("Invalid event format. Use: event <desc> /from <start> /to <end>");
                    break;
                }
                String start = times[0].trim();
                String end = times[1].trim();
                Task event_Task = new Event(desc, start, end);
                tasks.addTask(event_Task);
                storage.saveTasks(tasks.getTasks());
                ui.showTaskAdded(event_Task, tasks.getTasks().size());
            } catch (Exception e) {
                ui.showError("Invalid event format. Use: event <desc> /from <start> /to <end>");
            }
            break;

        case "delete":
            try {
                int idx = Integer.parseInt(args) - 1;
                Task removed = tasks.deleteTask(idx);
                storage.saveTasks(tasks.getTasks());
                ui.showTaskRemoved(removed, tasks.getTasks().size());
            } catch (Exception e) {
                ui.showError("Invalid task number.");
            }
            break;

        case "find":
            if (args == null || args.trim().isEmpty()) {
                ui.showError("Please input a keyword to search.");
                break;
            }

            // create temporary list to hold found tasks
            ArrayList<Task> filteredList = new ArrayList<>();
            for (Task task : tasks.getTasks()) {
                if (task.description.contains(args)) {
                    filteredList.add(task);
                }
            }

            if (filteredList.isEmpty()) {
                ui.showError("No matching tasks found.");
            } else {
                ui.showTaskList(filteredList);
            }
            break;

        default:
            ui.showError("Sorry, your command is beyond Sugon's Dictionary.");
        }
        return true; // continue running
    }
}