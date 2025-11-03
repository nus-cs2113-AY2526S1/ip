package darren;

import darren.task.Deadline;
import darren.task.Event;
import darren.task.Task;
import darren.task.Todo;

import java.io.IOException;

/**
 * Parses user input to determine the command and arguments
 * Handles integration between TaskList, Ui and Storage
 */
public class Parser {

    private static final int TODO_COMMAND_LENGTH = "todo ".length();
    private static final int DEADLINE_COMMAND_LENGTH = "deadline ".length();
    private static final int EVENT_COMMAND_LENGTH = "event ".length();
    private static final int FIND_COMMAND_LENGTH = "find ".length();


    private static Integer getInt(String input) throws DarrenException {
        String[] words = input.split(" ");
        if (words.length != 2) {
            throw new DarrenException("The command format for mark/unmark/delete is incorrect. Use '<command> <number>'.");
        }
        try {
            return Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new DarrenException("The number provided is not a valid task number.");
        }
    }

    public static boolean exit(String input) {
        return input.trim().equals("bye");
    }
/**
 * @param input The user's raw input string.
 * @param tasks The TaskList object containing the list of tasks.
 * @param ui The Ui object for displaying messages to the user.
 * @param storage The Storage object for saving and loading tasks.
 * @throws DarrenException For all user input errors
 * @throws IOException If there is an error saving tasks to storage.
 */
    public static void command(String input, TaskList tasks, Ui ui, Storage storage) throws DarrenException, IOException {
        input = input.trim();

        if (input.equals("bye")) {
            return;
        }

        if (input.equals("list")) {
            ui.displayList(tasks);
            return;
        }

        String[] words = input.split(" ");
        String command = words[0];

        switch (command) {
            case "mark":
                Integer markIndex = getInt(input);
                if (markIndex > tasks.size() || markIndex <= 0) {
                    throw new DarrenException("Please enter a valid task number to mark as done.");
                }
                tasks.get(markIndex - 1).markAsDone();
                storage.saveTasks(tasks.getTasks());
                ui.displayMark(tasks.get(markIndex - 1));
                break;

            case "unmark":
                Integer unmarkIndex = getInt(input);
                if (unmarkIndex > tasks.size() || unmarkIndex <= 0) {
                    throw new DarrenException("Please enter a valid task number to unmark as done.");
                }
                tasks.get(unmarkIndex - 1).markAsNotDone();
                storage.saveTasks(tasks.getTasks());
                ui.displayUnmark(tasks.get(unmarkIndex - 1));
                break;

            case "delete":
                Integer deleteIndex = getInt(input);
                if (deleteIndex > tasks.size() || deleteIndex <= 0) {
                    throw new DarrenException("Please enter a valid task number to delete.");
                }

                Task deletedTask = tasks.remove(deleteIndex - 1);
                storage.saveTasks(tasks.getTasks());
                ui.displayDelete(deletedTask, tasks);
                break;


            case "todo":
                if (input.length() <= TODO_COMMAND_LENGTH) {
                    throw new DarrenException("You forgot to specify your task");
                }
                String todoDescription = input.substring(TODO_COMMAND_LENGTH).trim();
                Task newTodo = new Todo(todoDescription);
                tasks.addTask(newTodo);
                storage.saveTasks(tasks.getTasks());
                ui.displayAdd(newTodo, tasks);
                break;

            case "deadline":
                if (input.length() <= DEADLINE_COMMAND_LENGTH) {
                    throw new DarrenException("You are missing a description!");
                }
                String removeDeadline = input.substring(DEADLINE_COMMAND_LENGTH).trim();
                String[] remaining = removeDeadline.split("/by");

                if (remaining.length < 2 || remaining[0].trim().isEmpty() || remaining[1].trim().isEmpty()) {
                    throw new DarrenException("Please use the correct format: deadline <description> /by <date/time>");
                }
                Task newDeadline = new Deadline(remaining[0].trim(), remaining[1].trim());
                tasks.addTask(newDeadline);
                storage.saveTasks(tasks.getTasks());
                ui.displayAdd(newDeadline, tasks);
                break;

            case "event":
                if (input.length() <= EVENT_COMMAND_LENGTH) {
                    throw new DarrenException("You are missing a description!");
                }
                String removeEvent = input.substring(EVENT_COMMAND_LENGTH).trim();
                String[] remainingEvent = removeEvent.split("/from");
                if (remainingEvent.length < 2) {
                    throw new DarrenException("Please use the correct format: Event <description> /from <date/time> /to <date/time>");
                }
                String description = remainingEvent[0].trim();
                String[] remainingEvent2 = remainingEvent[1].split("/to");
                if (remainingEvent2.length < 2) {
                    throw new DarrenException("Please use the correct format: Event <description> /from <date/time> /to <date/time>");
                }
                String from = remainingEvent2[0].trim();
                String to = remainingEvent2[1].trim();

                if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new DarrenException("Remember to fill up the description, start and end time of you event!");
                }
                Task newEvent = new Event(description, from, to);
                tasks.addTask(newEvent);
                storage.saveTasks(tasks.getTasks());
                ui.displayAdd(newEvent, tasks);
                break;

            case "find":
                if (input.length() <= FIND_COMMAND_LENGTH) {
                    throw new DarrenException("You are missing a keyword!");
                }
                String keyword = input.substring("find ".length()).trim();

                TaskList matchingTasks = tasks.findTasks(keyword);

                ui.displayFind(matchingTasks);
                break;

            default:
                throw new DarrenException("I'm sorry, I don't recognise that command. Please try again.");
        }
    }
}