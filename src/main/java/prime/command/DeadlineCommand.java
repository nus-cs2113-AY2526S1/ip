package prime.command;

import prime.exceptions.EmptyByException;
import prime.exceptions.EmptyDescriptionException;
import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.parser.CommandType;
import prime.parser.Parser;
import prime.task.Deadline;
import prime.ui.UserInterface;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface ui) throws PrimeException {
        String[] deadlineParts = Parser.parseDeadline(arguments);
        if (deadlineParts == null) {
            throw new PrimeException("Invalid deadline format! Use: deadline [desc] /by [time]");
        } else if (deadlineParts[0].isEmpty()) {
            throw new EmptyDescriptionException(CommandType.DEADLINE);
        } else if (deadlineParts[1].isEmpty()) {
            throw new EmptyByException(CommandType.DEADLINE);
        }
        taskManager.addTask(new Deadline(deadlineParts[0], deadlineParts[1]), ui);
    }
}
