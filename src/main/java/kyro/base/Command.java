package kyro.base;

import kyro.tasks.*;
import kyro.exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Abstract base type for all executable commands in Kyro.
 * <p>
 * Subclasses of {@code Command} implement specific user actions
 * from CommandList.
 */
public class Command {

    private final CommandList type;
    private final String arguments;

    public Command(CommandList type) {
        this(type, "");
    }

    public Command(CommandList type, String arguments) {
        this.type = type;
        this.arguments = arguments;
    }

    public boolean isExit() {
        return type == CommandList.BYE;
    }

    /**
     * Handles the logic for given command input.
     *
     * @param tasks   The task list to operate on.
     * @param printer The printer used for output messages.
     * @param storage The storage handler used to save data.
     * @throws KyroException If execution fails due to invalid input
     *                       or storage errors.
     */
    public void execute(TaskList tasks, Printer printer, Storage storage) throws KyroException {
        switch (type) {
        case BYE:
            printer.showBye();
            break;

        case LIST:
            printer.showTasks(tasks.getTaskList());
            break;

        case MARK:
            int markIndex = Integer.parseInt(arguments) - 1;
            Task markTask = tasks.get(markIndex);
            markTask.setDone(true);
            storage.save(tasks);
            printer.showMark(markTask);
            break;

        case UNMARK:
            int unmarkIndex = Integer.parseInt(arguments) - 1;
            Task unmarkTask = tasks.get(unmarkIndex);
            unmarkTask.setDone(false);
            storage.save(tasks);
            printer.showUnmark(unmarkTask);
            break;

        case TODO:
            if (arguments.trim().isEmpty()) {
                throw new KyroException("Todo format must be: <desc>");
            }
            Task todo = new Todo(arguments.trim());
            tasks.add(todo);
            storage.save(tasks);
            printer.showAdded(todo, tasks.size());
            break;

        case DEADLINE:
            String[] deadlineParts = arguments.split(" /by ", 2);
            if (deadlineParts.length < 2) {
                throw new KyroException("Deadline format must be: <desc> /by <yyyy-MM-dd HHmm>");
            }
            Task deadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            tasks.add(deadline);
            storage.save(tasks);
            printer.showAdded(deadline, tasks.size());
            break;

        case EVENT:
            String[] eventParts = arguments.split(" /from | /to ", 3);
            if (eventParts.length < 3) {
                throw new KyroException("Event format must be: <desc> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
            }
            Task event = new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
            tasks.add(event);
            storage.save(tasks);
            printer.showAdded(event, tasks.size());
            break;

        case DELETE:
            int deleteIndex = Integer.parseInt(arguments) - 1;
            Task deleteTask = tasks.get(deleteIndex);
            tasks.remove(deleteIndex);
            storage.save(tasks);
            printer.showDelete(deleteTask, tasks.size());
            break;

        case FILTER:
            try {
                LocalDate date = LocalDate.parse(arguments);
                ArrayList<Task> matchingDates = new ArrayList<>();
                for (Task task : tasks.getTaskList()) {
                    if (task instanceof Deadline d && d.getBy().toLocalDate().equals(date)) {
                        matchingDates.add(d);
                    }
                    if (task instanceof Event e &&
                            (e.getFrom().toLocalDate().equals(date) || e.getTo().toLocalDate().equals(date))) {
                        matchingDates.add(e);
                    }
                }
                if (matchingDates.isEmpty()) {
                    printer.showError("Kyro found no tasks on: " + date);
                } else {
                    printer.showTasks(matchingDates);
                }
            } catch (DateTimeParseException e) {
                throw new KyroException("Date format must be: <yyyy-MM-dd>");
            }
            break;

        case FIND:
            String keyword = arguments.toLowerCase();
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task : tasks.getTaskList()) {
                if (task.getDescription().toLowerCase().contains(keyword)) {
                    matchingTasks.add(task);
                }
            }
            if (matchingTasks.isEmpty()) {
                printer.showError("Kyro found no tasks matching: " + keyword);
            } else {
                printer.showFind(matchingTasks);
            }
            break;

        case HELP:
            printer.showHelp();
            break;

        default:
            throw new InvalidCommandException();
        }
    }
}