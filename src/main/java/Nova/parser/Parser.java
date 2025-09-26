package Nova.parser;

import Nova.command.Command;
import Nova.command.ExitCommand;
import Nova.command.FindCommand;
import Nova.command.ListCommand;
import Nova.command.MarkCommand;
import Nova.command.AddTodoCommand;
import Nova.command.AddDeadlineCommand;
import Nova.command.AddEventCommand;
import Nova.command.DeleteCommand;
import Nova.command.HelpCommand;


import Nova.exception.NovaException;

public class Parser {
    public static Command parseCommand(String userInput) throws NovaException {
        String[] inputArray = userInput.split(" ", 2);
        String commandWord = inputArray[0];
        String arguments = inputArray.length > 1 ? inputArray[1] : "";

        return switch (commandWord) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(arguments, true);
            case "unmark" -> new MarkCommand(arguments, false);
            case "todo" -> new AddTodoCommand(arguments);
            case "deadline" -> new AddDeadlineCommand(arguments);
            case "event" -> new AddEventCommand(arguments);
            case "delete" -> new DeleteCommand(arguments);
            case "find" -> new FindCommand(arguments);
            case "help" -> new HelpCommand();
            default -> throw new NovaException("I don't understand that command.");
        };
    }
}
