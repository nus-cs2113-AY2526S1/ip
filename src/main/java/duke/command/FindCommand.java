package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String entry;
    public FindCommand(String entry) {
        this.entry = entry;
    }

    /**
     * Find tasks containing the given the entry
     *
     * @param tasks which is a list of tasks under type TaskList
     * @param storage which is the instance used for reading and writing information into the given file path
     * @param ui which outputs information to the user to show message
     * @throws DukeException when the there are errors in the file
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        String output = tasks.findingTaskWithName(entry);
        if (output.isEmpty()) {
            ui.showError("uh oh... cant find such task: " + entry);
            return;
        }
        ui.showMessage("well, ive found these for you:");
        ui.showMessage(output);
    }
}