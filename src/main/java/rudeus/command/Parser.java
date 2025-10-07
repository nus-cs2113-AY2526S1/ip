package rudeus.command;

import rudeus.task.Deadline;
import rudeus.task.Event;
import rudeus.task.Task;
import rudeus.task.Todo;

/**
 * Parses user input to create Task objects.
 */
public class Parser {
    private enum TaskType {
        TODO, DEADLINE, EVENT, OTHER;

        public static TaskType fromString(String command) {
            return switch (command) {
            case "todo" -> TODO;
            case "deadline" -> DEADLINE;
            case "event" -> EVENT;
            default -> OTHER;
            };
        }
    }

    /**
     * Parses user input and returns the corresponding Task.
     *
     * @param userInput The user input string.
     * @return Task object corresponding to the input.
     * @throws IllegalArgumentException if the input is invalid.
     */
    public static Task parseTask(String userInput) {
        String trimmedInput = userInput.trim();
        String[] split = trimmedInput.split(" ", 2);
        String command = split[0];
        String args = split.length > 1 ? split[1].trim() : "";

        TaskType taskType = TaskType.fromString(command);

        switch (taskType) {
        case TODO:
            if (args.isEmpty()) {
                throw new IllegalArgumentException("You want a todo spell, but didn't say which!"
                        + " Even I can't read minds, you know?");
            }
            return new Todo(args);
        case DEADLINE:
            String[] parts = args.split(" /by ", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new IllegalArgumentException("Deadline spell needs both a description "
                        + "and a /by! Try again, apprentice!");
            }
            return new Deadline(parts[0].trim(), parts[1].trim());
        case EVENT:
            String[] firstSplit = args.split(" /from ", 2);
            if (firstSplit.length < 2) {
                throw new IllegalArgumentException("Event spell incomplete! Use /from and /to, or the magic fizzles.");
            }
            String desc = firstSplit[0].trim();
            String[] secondSplit = firstSplit[1].split(" /to ", 2);
            if (secondSplit.length < 2 || desc.isEmpty() || secondSplit[0].trim().isEmpty()
                    || secondSplit[1].trim().isEmpty()) {
                throw new IllegalArgumentException("Event spell incomplete! Use /from and /to, or the magic fizzles.");
            }
            return new Event(desc, secondSplit[0].trim(), secondSplit[1].trim());
        default:
            throw new IllegalArgumentException("Hmm? That spell doesn't exist. Try a todo, deadline, or event!");
        }
    }
}
