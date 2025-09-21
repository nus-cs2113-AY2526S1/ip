package prime.command;

import prime.manager.TaskManager;
import prime.ui.UserInterface;

public class ListCommand extends Command{
    public ListCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface ui){
        taskManager.listTasks(ui);
    }
}
