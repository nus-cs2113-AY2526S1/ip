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

/**
 * Represents a command to add an {@link Event} task to the task list.
 * <p>
 * The command requires a description, a start time (/from), and an end time (/to).
 * If any of these fields are missing or empty, the corresponding exception
 * ({@link EmptyDescriptionException}, {@link EmptyFromException}, {@link EmptyToException})
 * is thrown.
 * </p>
 */
public class EventCommand extends Command {

    /**
     * Constructs a new {@code EventCommand} with the specified arguments.
     *
     * @param arguments the full argument string containing description, /from, and /to
     */
    public EventCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the command by parsing the event details and adding a new
     * {@link Event} task to the {@link TaskManager}.
     *
     * @param taskManager the task manager handling all tasks
     * @param ui          the user interface to display messages
     * @throws PrimeException if the input format is invalid or any required field is empty
     */
    @Override
    public void execute(TaskManager taskManager, UserInterface ui) throws PrimeException {
        String[] eventParts = Parser.parseEvent(arguments);
        if (eventParts == null) {
            throw new PrimeException("Invalid event format! Use: event [desc] /from [start] /to [end]");
        } else if (eventParts[0].isEmpty()) {
            throw new EmptyDescriptionException(CommandType.EVENT);
        } else if (eventParts[1].isEmpty()) {
            throw new EmptyFromException(CommandType.EVENT);
        } else if (eventParts[2].isEmpty()) {
            throw new EmptyToException(CommandType.EVENT);
        }
        taskManager.addTask(new Event(eventParts[0], eventParts[1], eventParts[2]), ui);
    }
}
