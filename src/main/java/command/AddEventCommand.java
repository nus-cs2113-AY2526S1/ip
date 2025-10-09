package command;

import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;

/**
 * Command to add an Event task to the TaskList.
 */
public class AddEventCommand extends Command {
    private String taskDescription;
    private String from;
    private String to;

    /**
     * Constructs an AddEventCommand with the event details.
     *
     * @param taskDescription description of the event
     * @param from start time/date of the event
     * @param to end time/date of the event
     */
    public AddEventCommand(String taskDescription, String from, String to) {
        this.taskDescription = taskDescription;
        this.from = from;
        this.to = to;
    }

    /**
     * Indicates that this command does not exit the program.
     *
     * @return false since this is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command: creates an Event, adds it to the TaskList,
     * and notifies the user via the Ui.
     *
     * @param ui the user interface for printing messages
     * @param storage the storage (unused in this command)
     * @param listOfUserTasks the TaskList to add the event to
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList listOfUserTasks) {
        Event event = new Event(taskDescription, from, to);
        listOfUserTasks.add(event);
        ui.showAddedEvent(event);
    }
}
