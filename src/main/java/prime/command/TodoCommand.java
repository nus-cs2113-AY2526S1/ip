package prime.command;

import prime.exceptions.EmptyDescriptionException;
import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.parser.CommandType;
import prime.task.ToDo;
import prime.ui.UserInterface;

public class TodoCommand extends Command {
    public TodoCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface ui) throws PrimeException {
        if (arguments.isEmpty()) {
            throw new EmptyDescriptionException(CommandType.TODO);
        }
        taskManager.addTask(new ToDo(arguments), ui);
    }

}
