package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class WrongCommand extends Command {
    private String ErrorMessage;
    public WrongCommand(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ui.showError("Seems like there are some errors: "+ ErrorMessage);
    }
}
