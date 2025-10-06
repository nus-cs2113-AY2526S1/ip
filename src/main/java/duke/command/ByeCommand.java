package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute (TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ui.showGoodbye();
    }
    @Override
    public boolean isExit(){
        return true;
    }
}
