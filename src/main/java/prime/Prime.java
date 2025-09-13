package prime;

import prime.exceptions.EmptyByException;
import prime.exceptions.EmptyDescriptionException;
import prime.exceptions.EmptyFromException;
import prime.exceptions.InvalidCommandException;
import prime.exceptions.PrimeException;
import prime.parser.Command;
import prime.parser.CommandType;
import prime.parser.Parser;
import prime.manager.TaskManager;
import prime.task.Deadline;
import prime.task.Event;
import prime.task.ToDo;
import prime.ui.UserInterface;

import java.util.Scanner;

public class Prime {

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
    
        ui.printWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            String userInput = scanner.nextLine().trim();
            try {
                Command command = Parser.parse(userInput);
                ui.printLineBreak();

                switch (command.getType()) {
                case LIST:
                    taskManager.listTasks(ui);
                    break;
                case DELETE:
                    int deleteTaskNo = Integer.parseInt(command.getArguments());
                    taskManager.deleteTask(deleteTaskNo, ui);
                    break;
                case MARK, UNMARK:
                    int markTaskNo = Integer.parseInt(command.getArguments());
                    taskManager.toggleMark(markTaskNo, ui);
                    break;
                case TODO:
                    if (command.getArguments().isEmpty()) {
                        throw new EmptyDescriptionException(CommandType.TODO);
                    }
                    taskManager.addTask(new ToDo(command.getArguments()), ui);
                    break;
                case DEADLINE:
                    String[] deadlineParts = Parser.parseDeadline(command.getArguments());
                    if (deadlineParts == null) {
                        throw new PrimeException("Invalid deadline format! Use: deadline [desc] /by [time]");
                    } else if (deadlineParts[0].isEmpty()){
                        throw new EmptyDescriptionException(CommandType.DEADLINE);
                    } else if (deadlineParts[1].isEmpty()){
                        throw new EmptyByException(CommandType.DEADLINE);
                    }
                    taskManager.addTask(new Deadline(deadlineParts[0], deadlineParts[1]), ui);
                    break;
                case EVENT:
                    String[] eventParts = Parser.parseEvent(command.getArguments());
                    if (eventParts == null) {
                        throw new PrimeException("Invalid event format! Use: event [desc] /from [start] /to [end]");
                    } else if (eventParts[0].isEmpty()){
                        throw new EmptyDescriptionException(CommandType.EVENT);
                    } else if (eventParts[1].isEmpty()){
                        throw new EmptyFromException(CommandType.EVENT);
                    } else if (eventParts[2].isEmpty()){
                        throw new EmptyByException(CommandType.EVENT);
                    }
                    taskManager.addTask(new Event(eventParts[0], eventParts[1], eventParts[2]), ui);
                    break;
                case BYE:
                    isExit = true;
                    ui.printByeMessage();
                    break;
                default:
                    throw new InvalidCommandException();
                }
            } catch (PrimeException e) {
                ui.printIndented(e.getMessage());
            } catch (NumberFormatException e) {
                ui.printIndented("Please enter a valid number!");
            }
            if (!isExit) {
                ui.printLineBreak();
            }
        }

        scanner.close();
    }
}
