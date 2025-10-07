package prime.command;

import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.parser.CommandType;
import prime.ui.UserInterface;

/**
 * Abstract base class representing a user command in the Prime task management system.
 * <p>
 * Each concrete subclass corresponds to a specific command type, such as
 * {@code TodoCommand}, {@code DeadlineCommand}, {@code EventCommand}, {@code MarkCommand}, etc.
 * </p>
 */
public abstract class Command {
    protected String arguments;
    protected CommandType commandType;

    /**
     * Constructs a new {@code Command} with the specified arguments.
     *
     * @param arguments the arguments provided by the user
     */
    public Command(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns the raw arguments of this command.
     *
     * @return the arguments as a string
     */
    public String getArguments() {
        return arguments;
    }

    /**
     * Executes this command using the provided {@link TaskManager} and {@link UserInterface}.
     * <p>
     * Concrete subclasses must implement this method to perform the actual command logic.
     * </p>
     *
     * @param taskManager the task manager handling all tasks
     * @param ui          the user interface to display messages
     * @throws PrimeException if any error occurs during command execution
     */
    public abstract void execute(TaskManager taskManager, UserInterface ui) throws PrimeException;

    /**
     * Returns the type of this command.
     *
     * @return the command type
     */
    public CommandType getType() {
        return this.commandType;
    }

    /**
     * Returns whether this command should exit the program.
     * <p>
     * Defaults to {@code false}. Commands like {@code ByeCommand} should override
     * this method to return {@code true}.
     * </p>
     *
     * @return {@code true} if the command causes the application to exit, {@code false} otherwise
     */
    public boolean isExit() {
        return false;
    }
}
