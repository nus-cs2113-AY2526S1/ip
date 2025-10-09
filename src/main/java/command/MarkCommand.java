package command;
import exception.TwinException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

// Represents a command to mark a task as done
public class MarkCommand extends Command {
    // The index of the task to mark (1-based index as given by the user)
    private int itemNumber;

    // Constructor to initialize the command with the task number
    public MarkCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    // Marking a task does not exit the program
    @Override
    public boolean isExit() {
        return false;
    }

    // Executes the mark command:
    // 1. Check if the given item number is valid
    // 2. Retrieve the task from the task list
    // 3. Mark it as done
    // 4. Notify the user through the UI
    @Override
    public void execute(Ui ui, Storage storage, TaskList listOfUserTasks) throws TwinException {
        if (itemNumber > listOfUserTasks.size() || itemNumber <= 0) {
            // Throw an exception if the task number is invalid
            throw new TwinException("Please give me an item number that lies within the list!");
        }
        Task taskMarked = listOfUserTasks.get(itemNumber - 1); // fetch task by index
        taskMarked.markAsDone();                               // set status to done
        ui.showMarkedTask(taskMarked);                         // display confirmation
    }
}
