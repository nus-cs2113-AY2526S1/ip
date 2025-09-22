package prime.command;

import prime.exceptions.EmptyByException;
import prime.exceptions.EmptyDescriptionException;
import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.parser.CommandType;
import prime.parser.Parser;
import prime.task.Deadline;
import prime.ui.UserInterface;

/**
 * Represents a command to add a {@link Deadline} task to the task list.
 * <p>
 * The command requires a description and a deadline time (/by).
 * If either field is missing or empty, the corresponding exception
 * ({@link EmptyDescriptionException} or {@link EmptyByException}) is thrown.
 * </p>
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs a new {@code DeadlineCommand} with the specified arguments.
     *
     * @param arguments the full argument string containing description and /by
     */
    public DeadlineCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the command by parsing the deadline details and adding a new
     * {@link Deadline} task to the {@link TaskManager}.
     *
     * @param taskManager the task manager handling all tasks
     * @param ui          the user interface to display messages
     * @throws PrimeException if the input format is invalid or any required field is empty
     */
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
