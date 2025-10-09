package command;
import storage.Storage;
import exception.TwinException;
import task.Task;
import task.TaskList;
import ui.Ui;

// Represents a command to unmark a task (set it as not done)
public class UnMarkCommand extends Command {
    // The index of the task to unmark (1-based index as seen by user)
    private int itemNumber;

    // Constructor to initialize with the task number the user provided
    public UnMarkCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    // Unmarking a task does not exit the program
    @Override
    public boolean isExit() {
        return false;
    }

    // Executes the unmark command:
    // 1. Validate that the given task number is within range
    // 2. Retrieve the task from the task list
    // 3. Mark it as not done
    // 4. Show confirmation via UI
    @Override
    public void execute(Ui ui, Storage storage, TaskList listOfUserTasks) throws TwinException {
        if (itemNumber > listOfUserTasks.size() || itemNumber <= 0) {
            // If the index is invalid, throw an exception with a helpful message
            throw new TwinException("Please give me an item number that lies within the list!");
        }
        Task taskUnMarked = listOfUserTasks.get(itemNumber - 1); // get the task
        taskUnMarked.unMarkAsDone();                             // unmark it
        ui.showUnMarkedTask(taskUnMarked);                       // display update
    }
}
