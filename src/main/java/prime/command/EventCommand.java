package prime.command;

import prime.exceptions.EmptyDescriptionException;
import prime.exceptions.EmptyFromException;
import prime.exceptions.EmptyToException;
import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.parser.CommandType;
import prime.parser.Parser;
import prime.task.Event;
import prime.ui.UserInterface;

public class EventCommand extends Command{

    public EventCommand(String arguments) {
        super(arguments);
    }
    @Override
    public void execute(TaskManager taskManager, UserInterface ui) throws PrimeException{
        String[] eventParts = Parser.parseEvent(arguments);
        if (eventParts == null) {
            throw new PrimeException("Invalid event format! Use: event [desc] /from [start] /to [end]");
        } else if (eventParts[0].isEmpty()){
            throw new EmptyDescriptionException(CommandType.EVENT);
        } else if (eventParts[1].isEmpty()){
            throw new EmptyFromException(CommandType.EVENT);
        } else if (eventParts[2].isEmpty()){
            throw new EmptyToException(CommandType.EVENT);
        }
        taskManager.addTask(new Event(eventParts[0], eventParts[1], eventParts[2]), ui);
    }
}
