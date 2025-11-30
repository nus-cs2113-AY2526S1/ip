package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import storage.TaskStorage;
import exceptions.EmptyDescriptionException;
import exceptions.WrongTaskNumberException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import util.DateTimeUtil;

/**
 * Parser centralises command parsing and execution for the Bruh application.
 */
public class Parser {

    public static class Result {
        public final boolean exit;
        public final boolean modified;

        public Result(boolean exit, boolean modified) {
            this.exit = exit;
            this.modified = modified;
        }
    }

    /**
     * Parse and execute a command.
     *
     * @param storage TaskStorage instance (used for query helpers that load tasks)
     * @param tasks   current in-memory task list
     * @param input   full input split into [command, args]
     * @return Result containing exit flag and modified flag
     * @throws EmptyDescriptionException if a required description is empty
     * @throws WrongTaskNumberException  if a provided task index is invalid
     * @throws DateTimeParseException    if date parsing fails
     * @throws IOException               if storage calls fail
     */
    public static Result parse(TaskStorage storage, ArrayList<Task> tasks, String[] input)
            throws EmptyDescriptionException, WrongTaskNumberException, DateTimeParseException, IOException {
        boolean isTaskListModified = false;

        String command = input[0].toLowerCase();

        switch (command) {
        case "bye":
            System.out.println("Adios");
            return new Result(true, false);

        case "list":
            if (tasks.isEmpty()) {
                System.out.println("No tasks");
            } else {
                System.out.println("Here are your tasks: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i).toString());
                }
            }
            break;

        case "find":
            String keyword = input[1].trim();
            printTasksByKeyword(storage, keyword);
            break;

        case "todo":
            Todo newToDo = new Todo(input[1].trim());
            tasks.add(newToDo);
            System.out.println("Added todo: \n" + newToDo + "\n" + "You now have " + tasks.size() + " tasks.");
            isTaskListModified = true;
            break;

        case "deadline":
            String[] deadlineInput = input[1].split(" /by ", 2);
            if (deadlineInput.length < 2 || deadlineInput[1].isBlank()) {
                throw new EmptyDescriptionException();
            }
            Deadline newDeadline = new Deadline(deadlineInput[0].trim(), DateTimeUtil.parseString(deadlineInput[1]));
            tasks.add(newDeadline);
            System.out.println("Added deadline: \n" + newDeadline + "\n" + "You now have " + tasks.size() + " tasks.");
            isTaskListModified = true;
            break;

        case "due":
            printTasksOnDate(storage, DateTimeUtil.parseString(input[1]));
            break;

        case "event":
            String[] eventInput = input[1].split((" /from | /to "), 3);
            if (eventInput.length < 3 || eventInput[1].isBlank() || eventInput[2].isBlank()) {
                throw new EmptyDescriptionException();
            }
            Event newEvent = new Event(eventInput[0].trim(), eventInput[1].trim(), eventInput[2].trim());
            tasks.add(newEvent);
            System.out.println("Added event: \n" + newEvent + "\n" + "You now have " + tasks.size() + " tasks.");
            isTaskListModified = true;
            break;

        case "mark":
            Task taskToMark = getTaskByIndex(tasks, input[1]);
            taskToMark.setDone();
            System.out.println("Marked as done: " + taskToMark);
            isTaskListModified = true;
            break;

        case "unmark":
            Task taskToUnmark = getTaskByIndex(tasks, input[1]);
            taskToUnmark.setUndone();
            System.out.println("Marked as undone: " + taskToUnmark);
            isTaskListModified = true;
            break;

        case "delete":
            if (input[1].trim().equals("all")) {
                System.out.println("Removing all tasks");
                tasks.clear();
                isTaskListModified = true;
                break;
            }
            Task taskToRemove = getTaskByIndex(tasks, input[1]);
            System.out.println("Successfully removed task " + input[1] + ":\n" + taskToRemove.toString());
            tasks.remove(taskToRemove);
            isTaskListModified = true;
            break;

        default:
            System.out.println("Huh?...");
        }

        return new Result(false, isTaskListModified);
    }

    private static void printTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    private static void printTasksOnDate(TaskStorage storage, LocalDateTime date)
            throws EmptyDescriptionException, IOException {
        List<Task> tasks = storage.tasksOnDate(date.toLocalDate());
        if (tasks.isEmpty()) {
            System.out.println("No tasks with date " + DateTimeUtil.prettyPrint(date));
        } else {
            System.out.println("Printing tasks with date: " + DateTimeUtil.prettyPrint(date));
            printTasks(tasks);
        }
    }

    private static void printTasksByKeyword(TaskStorage storage, String keyword)
            throws EmptyDescriptionException, IOException {
        List<Task> tasks = storage.tasksByKeyword(keyword);
        if (tasks.isEmpty()) {
            System.out.println("No tasks with keyword " + keyword);
        } else {
            System.out.println("Printing tasks with keyword: " + keyword);
            printTasks(tasks);
        }
    }

    private static Task getTaskByIndex(ArrayList<Task> tasks, String indexString) throws WrongTaskNumberException {
        try {
            int index = Integer.parseInt(indexString) - 1;
            if (index < 0 || index >= tasks.size())
                throw new WrongTaskNumberException();
            return tasks.get(index);
        } catch (NumberFormatException e) {
            throw new WrongTaskNumberException();
        }
    }
}
