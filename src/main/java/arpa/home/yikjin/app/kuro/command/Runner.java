package arpa.home.yikjin.app.kuro.command;

import java.util.Map;

import arpa.home.yikjin.app.kuro.exception.input.argument.ExcessTaskIdsException;
import arpa.home.yikjin.app.kuro.exception.input.argument.InvalidTaskIdException;
import arpa.home.yikjin.app.kuro.exception.input.argument.MissingTaskIdsException;
import arpa.home.yikjin.app.kuro.exception.input.argument.MissingTaskNameException;
import arpa.home.yikjin.app.kuro.exception.input.command.InvalidCommandException;
import arpa.home.yikjin.app.kuro.exception.input.command.MissingCommandException;
import arpa.home.yikjin.app.kuro.exception.input.option.MissingOptionNameException;
import arpa.home.yikjin.app.kuro.exception.input.option.MissingOptionValueException;
import arpa.home.yikjin.app.kuro.exception.state.EmptyTaskListException;
import arpa.home.yikjin.app.kuro.task.Deadline;
import arpa.home.yikjin.app.kuro.task.Event;
import arpa.home.yikjin.app.kuro.task.Task;
import arpa.home.yikjin.app.kuro.task.TaskManager;
import arpa.home.yikjin.app.kuro.task.Todo;
import arpa.home.yikjin.app.kuro.ui.Ui;

public final class Runner {
    private static final InputParser PARSER = new InputParser();
    private static boolean isTerminateGiven = false;

    public static void runCommand(final String commandStr) {
        PARSER.parse(commandStr);

        final String rawCommand = PARSER.getRawCommand();
        final InputCommand command = PARSER.getCommand();

        if (rawCommand.isEmpty()) {
            throw new MissingCommandException();
        }

        if (command == InputCommand.INVALID) {
            throw new InvalidCommandException(rawCommand);
        }

        final String[] posArgs = PARSER.getPositionalArgs();
        final String posArgsAsString = InputParser.mapArgsToString(posArgs);
        final Map<String, String[]> namedArgs = PARSER.getNamedArgs();

        switch (command) {
        case BYE:
            bye();
            break;
        case DEADLINE:
            addDeadline(posArgsAsString, namedArgs);
            break;
        case DELETE:
            removeTask(posArgs);
            break;
        case EVENT:
            addEvent(posArgsAsString, namedArgs);
            break;
        case LIST:
            listTasks();
            break;
        case MARK:
            markTasksAsDone(posArgs);
            break;
        case UNMARK:
            markTasksAsDone(posArgs, false);
            break;
        case TODO:
            addTodo(posArgsAsString);
            break;
        default:
            throw new InvalidCommandException(rawCommand);
        }
    }

    private static void bye() {
        Ui.bye();
        isTerminateGiven = true;
    }

    private static void addDeadline(final String deadlineName, final Map<String, String[]> deadlineArgs) {
        if (deadlineName.isEmpty()) {
            throw new MissingTaskNameException("deadline");
        }

        final String[] byArr = deadlineArgs.get("by");
        if (byArr == null) {
            throw new MissingOptionNameException("deadline", "by", "string");
        }

        final String by = InputParser.mapArgsToString(byArr);
        if (by.isEmpty()) {
            throw new MissingOptionValueException("deadline", "by", "string");
        }

        final Deadline deadline = new Deadline(deadlineName, by);
        addTask(deadline);
    }

    private static void removeTask(final String[] taskIds) {
        if (taskIds.length < 1) {
            throw new MissingTaskIdsException();
        }

        if (taskIds.length > 1) {
            throw new ExcessTaskIdsException();
        }

        final String taskIdStr = taskIds[0];
        final int taskId;

        try {
            taskId = Integer.parseInt(taskIdStr);
        } catch (final NumberFormatException ignored) {
            throw new InvalidTaskIdException(taskIdStr);
        }

        if (taskId < 1 || taskId > TaskManager.getNumTasks()) {
            throw new InvalidTaskIdException(taskId);
        }

        final int taskIndex = taskId - 1;
        final Task task = TaskManager.getTask(taskIndex);

        Ui.removeTaskBegin();
        Ui.listTaskDetails(taskId, task);

        TaskManager.removeTask(taskIndex);

        Ui.tasksCount(TaskManager.getNumTasks());
    }

    private static void addEvent(final String eventName, final Map<String, String[]> eventArgs) {
        if (eventName.isEmpty()) {
            throw new MissingTaskNameException("event");
        }

        final String[] fromArr = eventArgs.get("from");
        if (fromArr == null) {
            throw new MissingOptionNameException("event", "from", "string");
        }

        final String from = InputParser.mapArgsToString(fromArr);
        if (from.isEmpty()) {
            throw new MissingOptionValueException("event", "from", "string");
        }

        final String[] toArr = eventArgs.get("to");
        if (toArr == null) {
            throw new MissingOptionNameException("event", "to", "string");
        }

        final String to = InputParser.mapArgsToString(toArr);
        if (to.isEmpty()) {
            throw new MissingOptionValueException("event", "to", "string");
        }

        final Event event = new Event(eventName, from, to);
        addTask(event);
    }

    private static void listTasks() {
        if (!TaskManager.hasTasks()) {
            throw new EmptyTaskListException();
        }

        final int numTasks = TaskManager.getNumTasks();
        Ui.listTasksBegin();

        for (int i = 0; i < numTasks; i++) {
            final int listNumber = i + 1;
            final Task task = TaskManager.getTask(i);

            Ui.listTaskDetails(listNumber, task);
        }
    }

    private static void markTasksAsDone(final String[] taskIds) {
        markTasksAsDone(taskIds, true);
    }

    private static void markTasksAsDone(final String[] taskIds, final boolean isDone) {
        if (taskIds.length < 1) {
            throw new MissingTaskIdsException();
        }

        if (isDone) {
            Ui.markTasksBegin();
        } else {
            Ui.unmarkTasksBegin();
        }

        for (final String taskIdString : taskIds) {
            markSingleTaskAsDone(taskIdString, isDone);
        }
    }

    private static void markSingleTaskAsDone(final String taskIdStr, final boolean isDone) {
        final int taskId;

        try {
            taskId = Integer.parseInt(taskIdStr);
        } catch (final NumberFormatException ignored) {
            throw new InvalidTaskIdException(taskIdStr);
        }

        if (taskId < 1 || taskId > TaskManager.getNumTasks()) {
            throw new InvalidTaskIdException(taskId);
        }

        final Task task = TaskManager.getTask(taskId - 1);
        task.setDone(isDone);

        Ui.listTaskDetails(taskId, task);
    }

    private static void addTodo(final String todoName) {
        if (todoName.isEmpty()) {
            throw new MissingTaskNameException("todo");
        }

        final Todo todo = new Todo(todoName);
        addTask(todo);
    }

    private static void addTask(final Task task) {
        TaskManager.addTask(task);
        Ui.addedTask(task, TaskManager.getNumTasks());
    }

    public static boolean isTerminateGiven() {
        return isTerminateGiven;
    }
}
