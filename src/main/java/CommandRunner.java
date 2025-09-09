import java.util.Map;

final class CommandRunner {
    private static final InputParser PARSER = new InputParser();
    private static boolean isTerminateGiven = false;

    static void runCommand(final String command) {
        PARSER.parse(command);

        final String[] posArgs = PARSER.getPositionalArgs();
        final String posArgsAsString = String.join(" ", posArgs);
        final Map<String, String[]> namedArgs = PARSER.getNamedArgs();

        switch (PARSER.getCommand()) {
        case BYE:
            bye();
            break;
        case DEADLINE:
            addDeadline(posArgsAsString, namedArgs);
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
        default:
            addTodo(posArgsAsString);
            break;
        }
    }

    static void bye() {
        Ui.bye();
        isTerminateGiven = true;
    }

    private static void addDeadline(final String deadlineName, final Map<String, String[]> deadlineArgs) {
        final String by = InputParser.mapArgsToString(deadlineArgs.get("by"));
        final Deadline deadline = new Deadline(deadlineName, by);

        addTask(deadline);
    }

    private static void addEvent(final String eventName, final Map<String, String[]> eventArgs) {
        final String from = InputParser.mapArgsToString(eventArgs.get("from"));
        final String to = InputParser.mapArgsToString(eventArgs.get("to"));
        final Event event = new Event(eventName, from, to);

        addTask(event);
    }

    private static void listTasks() {
        if (!TaskManager.hasTasks()) {
            Ui.errNoTasks();
            return;
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
            Ui.errNoTaskIds();
            return;
        }

        if (isDone) {
            Ui.markTasksBegin();
        } else {
            Ui.unmarkTasksBegin();
        }

        for (final String taskIdString : taskIds) {
            final int taskId;

            try {
                taskId = Integer.parseInt(taskIdString);
            } catch (final NumberFormatException ignored) {
                Ui.errInvalidTaskId(taskIdString);
                continue;
            }

            if (taskId < 1 || taskId > TaskManager.getNumTasks()) {
                Ui.errInvalidTaskId(taskId);
                continue;
            }

            final Task task = TaskManager.getTask(taskId - 1);
            task.setDone(isDone);

            Ui.listTaskDetails(taskId, task);
        }
    }

    private static void addTodo(final String todoName) {
        final Todo todo = new Todo(todoName);
        addTask(todo);
    }

    private static void addTask(final Task task) {
        TaskManager.addTask(task);
        Ui.addedTask(task, TaskManager.getNumTasks());
    }

    static boolean isTerminateGiven() {
        return isTerminateGiven;
    }
}
