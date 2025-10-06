package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class WeirdCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ui.showMessage("sorry, gordon is too dumb to understand you command :(( could u plz try another one?");
    }
}
