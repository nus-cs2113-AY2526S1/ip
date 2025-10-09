package command;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

// Represents a command to add a Deadline task
public class AddDeadlineCommand extends Command {
    // Description of the deadline task
    private String taskDescription;
    // The due date/time (by when the task must be done)
    private String by;

    // Constructor to initialize description and deadline time
    public AddDeadlineCommand(String taskDescription, String by) {
        this.taskDescription = taskDescription;
        this.by = by;
    }

    // Adding a deadline command does not cause the program to exit
    @Override
    public boolean isExit() {
        return false;
    }

    // Executes the add-deadline command:
    // 1. Create a new Deadline object
    // 2. Add it to the user's task list
    // 3. Notify the user via the UI
    @Override
    public void execute(Ui ui, Storage storage, TaskList listOfUserTasks) {
        Deadline deadline = new Deadline(taskDescription, by); // create deadline
        listOfUserTasks.add(deadline); // add it to the list
        ui.showAddedDeadline(deadline); // display confirmation to user
    }
}
