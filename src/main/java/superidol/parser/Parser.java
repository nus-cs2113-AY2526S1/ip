package superidol.parser;

import superidol.command.*;
import superidol.storage.Storage;
import superidol.task.Deadline;
import superidol.task.Event;
import superidol.task.Todo;
import superidol.tasklist.TaskList;
import superidol.ui.Ui;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static String deadlineRegex = "(deadline)\\s(.+?)\\s/by\\s(.+)";
    private static String eventRegex = "(event)\\s(.+?)\\s/from(.+?)\\s/to(.+)";

    public static Command parse(String command) {
        String commandKeyWord = command.split(" ")[0].toLowerCase();
        try {
            switch (commandKeyWord) {
            case "exit":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                try {
                    int taskId = Integer.parseInt(command.split(" ")[1]);
                    return new MarkCommand(taskId, true);
                } catch (NumberFormatException e) {
                    Ui.respondInvalidMarking();
                    return null;
                }
            case "unmark":
                try {
                    int taskId = Integer.parseInt(command.split(" ")[1]);
                    return new MarkCommand(taskId, false);
                } catch (NumberFormatException e) {
                    Ui.respondInvalidMarking();
                    return null;
                }
            case "todo": {
                // remove "todo"
                String task = command.substring(4).trim();
                if (task.isBlank()) {
                    Ui.respondInvalidTodo();
                    return null;
                } else {
                    return new AddTodoCommand(task);
                }
            }
            case "deadline": {
                Pattern pattern = Pattern.compile(deadlineRegex);
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String task = matcher.group(2).trim();
                    String time = matcher.group(3).trim();

                    return new AddDeadlineCommand(task, time);
                } else {
                    Ui.respondInvalidDeadline();
                    return null;
                }
            }
            case "event": {
                Pattern pattern = Pattern.compile(eventRegex);
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String task = matcher.group(2).trim();
                    String startTime = matcher.group(3).trim();
                    String endTime = matcher.group(4).trim();
                    return new AddEventCommand(task, startTime, endTime);
                } else {
                    Ui.respondInvalidEvent();
                    return null;
                }
            }
            case "delete":
                try {
                    int taskId = Integer.parseInt(command.split(" ")[1]);
                    return new DeleteCommand(taskId);
                } catch (NumberFormatException e) {
                    Ui.respondInvalidDeleting();
                    return null;
                }
            default:
                Ui.respondToInvalidCommand();
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.respondToInvalidCommand();
            return null;
        }
    }

    public void addDeadline(String command) {

    }

    public void deleteTask(String command) {

    }



}
