package commands;

import java.util.List;

import tasks.TaskList;
import tasks.Task;
import ui.Ui;
import exceptions.PepException;

/**
 * Represents a command that searches for tasks containing a given keyword.
 */
public class FindCommand extends Command {
    private final String description;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param description the keyword to search for in task descriptions
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the find command by searching the TaskList for matching tasks
     * and displaying them through the Ui.
     *
     * @param tasks the TaskList to search
     * @param ui the Ui to display results
     * @throws PepException if the keyword is missing or invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws PepException {
        if (description == null || description.trim().isEmpty()) {
            throw new PepException("Find command needs a description. Try: find <keyword>");
        }
        List<Task> matches = tasks.findTasks(description.trim());
        ui.showTasks(matches);
    }
}
