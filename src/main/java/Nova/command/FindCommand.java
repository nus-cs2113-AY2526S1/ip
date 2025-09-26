package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.TaskList;
import Nova.ui.TextUi;


public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new NovaException("Invalid keyword! Please use this format: find <desc>");
        }
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.showAllTasks(matchingTasks, keyword);
    }
}
