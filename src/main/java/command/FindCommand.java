package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Command to find tasks that contain a specific keyword in their description.
 */
public class FindCommand extends Command {
    /** The keyword to search for */
    private String keyword;

    /**
     * Constructs a FindCommand with the given search keyword.
     *
     * @param keyword the keyword to find in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * FindCommand does not cause the program to exit.
     *
     * @return false, since this command does not exit the application
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the find operation: searches for tasks containing the keyword
     * and displays the matching tasks through the UI.
     *
     * @param ui the UI instance for displaying output
     * @param storage the storage instance (unused here, but required by Command signature)
     * @param listOfUserTasks the TaskList containing all tasks to search
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList listOfUserTasks) {
        ArrayList<Task> matched = new ArrayList<>();
        for (Task t : listOfUserTasks.getAllTasks()) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matched.add(t);
            }
        }

        if (matched.isEmpty()) {
            ui.showError("No tasks match your search: \"" + keyword + "\"");
        } else {
            ui.showFoundTasks(matched);
        }
    }
}
