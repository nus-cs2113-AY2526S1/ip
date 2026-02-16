package nomnom.commands;

import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

public class InvalidCommand extends Command{
    private final String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printError(message);
        return false;
    }
}
