package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    int  index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * mark a task as done given the index
     *
     * @param tasks which is a list of tasks under type TaskList
     * @param storage which is the instance used for reading and writing information into the given file path
     * @param ui which outputs information to the user to show message
     * @throws DukeException when the there are errors in the file
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {

        if (index < 0 || index >= tasks.getSize()){
            ui.showError("emmm, does this task exist?");
            return;
        }

        tasks.getTask(index).setDone(true);
        storage.SaveTasks(tasks);
        ui.showMessage("Nice! I've marked this task as done:");
        System.out.println(tasks.getTask(index).printinfo());
    }
}
