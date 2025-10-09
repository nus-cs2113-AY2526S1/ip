package command;
import exception.TwinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

// Abstract base class for all commands in the Twin app
public abstract class Command {
    // Determines if executing this command should exit the application
    public abstract boolean isExit();

    // Executes the command with the provided UI, storage, and task list
    // All commands may throw TwinException for user-facing errors
    public abstract void execute(Ui ui, Storage storage, TaskList listOfUserTasks) throws TwinException;
}
