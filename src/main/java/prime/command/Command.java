package prime.command;

import prime.exceptions.PrimeException;
import prime.manager.TaskManager;
import prime.parser.CommandType;
import prime.ui.UserInterface;

public abstract class Command {
    protected String arguments;
    protected CommandType commandType;

    public Command(String arguments) {
        this.arguments = arguments;
    }

    public String getArguments() {
        return arguments;
    }

    public abstract void execute(TaskManager taskManager, UserInterface ui) throws PrimeException;

    public CommandType getType() {
        return this.commandType;
    }

    public boolean isExit() {
        return false;
    }
}
