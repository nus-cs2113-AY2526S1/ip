package prime.command;

import prime.manager.TaskManager;
import prime.ui.UserInterface;

public class ByeCommand extends Command {
    public ByeCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface ui) {
        ui.printByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
