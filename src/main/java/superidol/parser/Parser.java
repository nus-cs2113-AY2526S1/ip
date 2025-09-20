package superidol.parser;

import superidol.task.Deadline;
import superidol.task.Event;
import superidol.task.Todo;
import superidol.tasklist.TaskList;
import superidol.ui.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private TaskList taskList;

    private String deadlineRegex = "(deadline)\\s(.+?)\\s/by\\s(.+)";
    private String eventRegex = "(event)\\s(.+?)\\s/from(.+?)\\s/to(.+)";

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void addDeadline(String command) {
        Pattern pattern = Pattern.compile(deadlineRegex);
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            String task = matcher.group(2).trim();
            String time = matcher.group(3).trim();

            Deadline deadline = new Deadline(task, time);
            taskList.addTask(deadline, true);
        } else {
            Ui.respondInvalidDeadline();
        }
    }

    public void addEvent(String command) {
        Pattern pattern = Pattern.compile(eventRegex);
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            String task = matcher.group(2).trim();
            String startTime = matcher.group(3).trim();
            String endTime = matcher.group(4).trim();

            Event event = new Event(task, startTime, endTime);
            taskList.addTask(event, true);
        } else {
            Ui.respondInvalidEvent();
        }
    }

    public void addTodo(String command) {
        // remove "todo"
        String task = command.substring(4).trim();
        if (task.isBlank()) {
            Ui.respondInvalidTodo();
        } else {
            Todo todo = new Todo(task);
            taskList.addTask(todo, true);
        }
    }

    public void deleteTask(String command) {
        try {
            int taskId = Integer.parseInt(command.split(" ")[1]);
            taskList.deleteTask(taskId);
        } catch (NumberFormatException e) {
            Ui.respondInvalidDeleting();
        }
    }

    public void mark(String command, boolean isDone) {
        try {
            int taskId = Integer.parseInt(command.split(" ")[1]);
            taskList.markTask(taskId, isDone);
        } catch (NumberFormatException e) {
            Ui.respondInvalidMarking();
        }
    }

}
