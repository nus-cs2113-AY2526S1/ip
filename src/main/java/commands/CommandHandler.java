package commands;

import tasks.TaskList;
import messages.MessageHandler;

/**
 * Handles all command-related logic for the Hero application
 * This class serves as a central point for processing user commands,
 * calling the appropriate methods in the TaskList and MessageHandler classes,
 * and decoupling command logic from the main Hero class
 */
public class CommandHandler {

    /**
     * Handles the "list" command by displaying all tasks in the task list
     *
     * @param taskList The TaskList object containing the tasks
     */
    public static void handleTaskList(TaskList taskList) {
        MessageHandler.sendTaskList(taskList);
    }

    /**
     * Handles the "mark" command by marking a specified task as done
     *
     * @param taskList The TaskList object containing the tasks
     * @param content  The string containing the task number to mark
     */
    public static void handleMark(TaskList taskList, String content) {
        int taskNumber = parseAndValidateTaskNumber(content, taskList.getCount());
        if (taskNumber != -1) {
            taskList.mark(taskNumber);
        }
    }

    /**
     * Handles the "unmark" command by marking a specified task as not done
     *
     * @param taskList The TaskList object containing the tasks
     * @param content  The string containing the task number to unmark
     */
    public static void handleUnmark(TaskList taskList, String content) {
        int taskNumber = parseAndValidateTaskNumber(content, taskList.getCount());
        if (taskNumber != -1) {
            taskList.unmark(taskNumber);
        }
    }

    /**
     * Handles the "delete" command by deleting a specified task from the list
     *
     * @param taskList The TaskList object containing the tasks
     * @param content  The string containing the task number to delete
     */
    public static void handleDelete(TaskList taskList, String content) {
        int taskNumber = parseAndValidateTaskNumber(content, taskList.getCount());
        if (taskNumber != -1) {
            taskList.deleteFromTaskList(taskNumber);
        }
    }

    /**
     * Handles the to-do command by adding a new to-do task to the list
     *
     * @param taskList    The TaskList object to add the task to
     * @param description The description of the to-do task
     */
    public static void handleToDo(TaskList taskList, String description) {
        taskList.addToDoToTaskList(description);
    }

    /**
     * Handles the deadline command by adding a new Deadline task to the list
     *
     * @param taskList    The TaskList object to add the task to
     * @param description The description of the deadline task
     * @param by          The deadline date/time
     */
    public static void handleDeadline(TaskList taskList, String description, String by) {
        taskList.addDeadlineToTaskList(description, by);
    }

    /**
     * Handles the event command by adding a new Event task to the list
     *
     * @param taskList    The TaskList object to add the task to
     * @param description The description of the event
     * @param from        The start time of the event
     * @param to          The end time of the event
     */
    public static void handleEvent(TaskList taskList, String description, String from, String to) {
        taskList.addEventToTaskList(description, from, to);
    }

    /**
     * Handle the find command by searching for tasks with a matching keyword
     *
     * @param taskList The TaskList object to search within
     * @param text     The keyword to search for
     */
    public static void handleFind(TaskList taskList, String text) {
        taskList.find(text);
    }

    /**
     * Parses a task number and validates it.
     *
     * @param input the string to parse
     * @param max   the maximum valid task number
     * @return the parsed task number if valid, or -1 if invalid
     */
    private static int parseAndValidateTaskNumber(String input, int max) {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            MessageHandler.sendMessage("Invalid task number!");
            return -1;
        }

        if (taskNumber < 1 || taskNumber > max) {
            MessageHandler.sendMessage("There is no such task number!");
            return -1;
        }

        return taskNumber;
    }

}
