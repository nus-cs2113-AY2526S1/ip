package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.TaskList;
import Nova.ui.TextUi;

/**
 * Represents a command to find tasks in the task list
 * that match a given keyword in the task description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     * @param keyword The search keyword that user inputs.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching keyword in the task list for
     * tasks containing the keyword and displaying them to the user by the UI.
     *
     * @param tasks The TaskList containing all the tasks.
     * @param ui The TextUi object used to interact with the user.
     * @param storage The Storage object used for saving/loading tasks.
     * @throws NovaException If the keyword is invalid or empty.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new NovaException("Invalid keyword! Please use this format: find <desc>");
        }
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.showAllTasks(matchingTasks, keyword);
    }
}
