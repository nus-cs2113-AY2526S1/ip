package chara;

import java.util.ArrayList;
import java.util.List;

import chara.exception.BadFormatException;
import chara.exception.CharaException;
import chara.exception.EmptyDescriptionException;
import chara.exception.UnknownCommandException;
import chara.task.Deadline;
import chara.task.Event;
import chara.task.Task;
import chara.task.Todo;

/**
 * Interprets user input and determines the intended command.
 * Provides helpers to parse commands like add, mark, delete, and find,
 * and raises exceptions for invalid or badly formatted input.
 */
public class Parser {

    /**
     * Checks whether the given string can be parsed as an integer.
     *
     * @param input string to check
     * @return true if the string represents an integer, false otherwise
     */
    public static boolean isInteger(String input) {
        if (input == null) {
            return false;
        }
        try {
            Integer.parseInt(input.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Handles mark and unmark commands to change the completion status of tasks.
     * Throws a CharaException if the command is formatted incorrectly or refers to
     * an invalid task number.
     *
     * @param input user input string starting with "mark" or "unmark"
     * @param tasks the ArrayList of current tasks
     * @throws CharaException if the command format is invalid or the task number is out of range
     */
    public static void handleMarkCommands(String input, TaskList tasks, Ui ui) throws CharaException {
        String[] parts = input.split("\\s+", 2);
        boolean hasTwoParts = parts.length == 2;
        boolean isMark = parts[0].equals("mark");
        boolean isUnmark = parts[0].equals("unmark");

        if (isMark || isUnmark) {
            if (!hasTwoParts || !isInteger(parts[1])) {
                throw new BadFormatException("Use this format: \"" + parts[0] + " <task_number>\" =)");
            }
            int taskNum = Integer.parseInt(parts[1].trim());
            if (taskNum <= 0 || taskNum > tasks.size()) {
                ui.printTaskDoesNotExist();
                return;
            }
            Task t = tasks.get(taskNum - 1);
            if (isMark) {
                t.markAsDone();
                ui.printMarked(t);
            } else {
                t.markAsNotDone();
                ui.printUnmarked(t);
            }
        }
    }

    /**
     * Handles the delete command to remove a task from the list.
     * Throws a CharaException if the command is formatted incorrectly or refers to
     * an invalid task number.
     *
     * @param input user input string starting with "delete"
     * @param tasks the ArrayList of current tasks
     * @throws CharaException if the command format is invalid or the task number is out of range
     */
    public static void handleDeleteCommand(String input, TaskList tasks, Ui ui) throws CharaException {
        String[] parts = input.split("\\s+", 2);
        if (parts.length != 2 || !isInteger(parts[1])) {
            throw new BadFormatException("Use this format: \"delete <task_number>\" =)");
        }
        int taskNum = Integer.parseInt(parts[1].trim());
        if (taskNum <= 0 || taskNum > tasks.size()) {
            ui.printTaskDoesNotExist();
            return;
        }
        Task removed = tasks.remove(taskNum - 1);
        ui.printDeleted(removed, tasks.size());
    }

    /**
     * Handles the "find" command by searching for tasks whose descriptions
     * contain the given keyword. Uses {@link TaskList#find(String)} to get
     * matching tasks, and prints the results with their original indices.
     *
     * @param input the full user input string starting with "find"
     * @param tasks the TaskList containing all current tasks
     * @param ui the Ui instance for printing results
     * @throws BadFormatException if the command does not include a keyword
     */
    public static void handleFindCommand(String input, TaskList tasks, Ui ui) throws BadFormatException {
        String prefix = "find";
        String remainder = input.substring(prefix.length()).trim();

        boolean hasKeyword = !remainder.isEmpty();
        if (!hasKeyword) {
            throw new BadFormatException("Use this format: \"find <keyword>\" =)");
        }

        TaskList results = tasks.find(remainder);

        List<Integer> matchingIndices = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (results.contains(tasks.get(i))) {
                matchingIndices.add(i + 1); // keep 1-based indexing
            }
        }

        ui.printFindResultsWithIndices(tasks, matchingIndices);
    }



    /**
     * Creates the appropriate Task subclass (Todo, Deadline, Event) from user input.
     * Throws a chara.exception.CharaException if the input is not a valid command or has the wrong format.
     *
     * @param input user input string
     * @return the created chara.task.Task object
     * @throws CharaException if the input is invalid or does not match any command
     */
    public static Task createTaskFromInput(String input) throws CharaException {
        if (input.startsWith("todo")) {
            String description = input.substring("todo".length()).trim();
            if (description.isEmpty()) {
                throw new EmptyDescriptionException("todo");
            }
            return new Todo(description);
        }

        if (input.startsWith("deadline")) {
            String s = input.substring("deadline".length()).trim();
            if (!s.contains("/by")) {
                throw new BadFormatException(
                        "Use this format: \"deadline <description> /by <when>\" =)"
                );
            }
            String[] parts = s.split("/by", 2);
            String description = parts[0].trim();
            String by = parts[1].trim();
            if (description.isEmpty()) {
                throw new EmptyDescriptionException("deadline");
            }
            if (by.isEmpty()) {
                throw new BadFormatException("Your deadline needs a /by time!");
            }
            return new Deadline(description, by);
        }

        if (input.startsWith("event")) {
            String s = input.substring("event".length()).trim();
            if (!s.contains("/from") || !s.contains("/to")) {
                throw new BadFormatException(
                        "Use this format: \"event <description> /from <start> /to <end>\" =)"
                );
            }
            int fromIndex = s.indexOf("/from");
            int toIndex = s.indexOf("/to");
            String description = s.substring(0, fromIndex).trim();
            String from = s.substring(fromIndex + "/from".length(), toIndex).trim();
            String to = s.substring(toIndex + "/to".length()).trim();

            if (description.isEmpty()) {
                throw new EmptyDescriptionException("event");
            }
            if (from.isEmpty() || to.isEmpty()) {
                throw new BadFormatException(
                        "Your event needs both /from and /to times!!"
                );
            }
            return new Event(description, from, to);
        }

        // nothing matched
        throw new UnknownCommandException();
    }
}
