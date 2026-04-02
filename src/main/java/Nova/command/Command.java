package Nova.command;

import Nova.exception.NovaException;
import Nova.storage.Storage;
import Nova.task.TaskList;
import Nova.ui.TextUi;

/**
 * Represents an abstract command in the Nova chatbot.
 * Each command encapsulates a user action such as adding, deleting, ot listing
 * tasks. Concrete command classes must implement the {@code execute} method.
 */
public abstract class Command {
    /**
     * Executes the command with access to the task list, UI and storage.
     *
     * @param tasks The TaskList containing the current tasks.
     * @param ui The TextUi instance for displaying messages to the user.
     * @param storage The Storage instance for saving/loading the updated task list.
     * @throws NovaException If command cannot be executed due to invalid input
     * or errors.
     */
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage) throws NovaException;

    /**
     * Returns whether this command signals the application to exit.
     * By default, commands do not exit.
     * Commands like {@code ExitCommand} overrides this.
     *
     * @return {@code true} if this command ends the application,
     *         {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
