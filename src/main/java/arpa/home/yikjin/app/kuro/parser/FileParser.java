package arpa.home.yikjin.app.kuro.parser;

import java.util.ArrayList;
import java.util.regex.Pattern;

import arpa.home.yikjin.app.kuro.exception.io.InvalidFileLineException;
import arpa.home.yikjin.app.kuro.task.Deadline;
import arpa.home.yikjin.app.kuro.task.Event;
import arpa.home.yikjin.app.kuro.task.Task;
import arpa.home.yikjin.app.kuro.task.TaskType;
import arpa.home.yikjin.app.kuro.task.Todo;

public class FileParser {
    private static final Pattern CSV_DELIMITER = Pattern.compile("\\s*,\\s*");
    private Task parsedTask;

    public FileParser() {
        reset();
    }

    public void reset() {
        parsedTask = null;
    }

    public static String parseTaskAsLine(final Task task) {
        final ArrayList<String> res = new ArrayList<>(3);
        final TaskType taskType = task.getTaskType();

        res.add(taskType.toString());
        res.add(task.isDone() ? "1" : "");
        res.add(task.getName());

        switch (taskType) {
        case DEADLINE:
            final Deadline deadline = (Deadline) task;
            res.add(deadline.getDue());
            break;
        case EVENT:
            final Event event = (Event) task;
            res.add(event.getFrom());
            res.add(event.getTo());
            break;
        case TODO:
        default:
            break;
        }

        return String.join(",", res);
    }

    public Task getParsedTask() {
        return parsedTask;
    }

    public void parseLineAsTask(final String line) {
        if (line.isBlank()) {
            return;
        }

        final String[] sections = CSV_DELIMITER.split(line);
        parseSections(sections);
    }

    private void parseSections(final String[] sections) {
        if (sections.length < 3) {
            throw new InvalidFileLineException("expected at least 3 fields, got " + sections.length + " instead!");
        }

        final String taskTypeStr = sections[0].stripLeading();
        final TaskType taskType = taskTypeStr.isBlank() ? TaskType.INVALID : TaskType.parse(taskTypeStr.charAt(0));
        final boolean isTaskDone = "1".equals(sections[1]);
        final String taskTitle = sections[2].stripTrailing();

        switch (taskType) {
        case DEADLINE:  // deadline
            if (sections.length != 4) {
                throw new InvalidFileLineException("deadline expects 4 fields, got " + sections.length + " instead!");
            }

            final String deadlineDue = sections[3].stripTrailing();

            if (deadlineDue.isBlank()) {
                throw new InvalidFileLineException("invalid deadline due field!");
            }

            parsedTask = new Deadline(taskTitle, deadlineDue);
            break;
        case EVENT:  // event
            if (sections.length != 5) {
                throw new InvalidFileLineException("event expects 5 fields, got " + sections.length + " instead!");
            }

            final String eventFrom = sections[3].stripTrailing();

            if (eventFrom.isBlank()) {
                throw new InvalidFileLineException("invalid event from field!");
            }

            final String eventTo = sections[4].stripTrailing();

            if (eventTo.isBlank()) {
                throw new InvalidFileLineException("invalid event to field!");
            }

            parsedTask = new Event(taskTitle, eventFrom, eventTo);
            break;
        case TODO:
            if (sections.length != 3) {
                throw new InvalidFileLineException("todo expects 3 fields, got " + sections.length + " instead!");
            }

            parsedTask = new Todo(taskTitle);
            break;
        default:
            throw new InvalidFileLineException("unknown task type!");
        }

        parsedTask.setDone(isTaskDone);
    }
}
