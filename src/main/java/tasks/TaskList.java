package tasks;

import java.util.ArrayList;

import messages.MessageHandler;

/**
 * Represents a list of tasks
 * It provides methods for adding, deleting, marking, and unmarking tasks
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from a given ArrayList of tasks
     *
     * @param taskList The ArrayList of tasks to initialize the list with
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Retrieves a task at a specific index
     *
     * @param index The index of the task to retrieve
     * @return The task at the specified index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the total number of tasks in the list
     *
     * @return The number of tasks
     */
    public int getCount() {
        return taskList.size();
    }

    /**
     * Adds a to-do task to the list
     *
     * @param task The description of the to-do
     */
    public void addToDoToTaskList(String task) {
        ToDo newTask = new ToDo(task);
        taskList.add(newTask);
        MessageHandler.sendAddTaskMessage(newTask, getCount());
    }

    /**
     * Adds a Deadline task to the list
     *
     * @param task     The description of the deadline
     * @param deadline The deadline time
     */
    public void addDeadlineToTaskList(String task, String deadline) {
        Deadline newTask = new Deadline(task, deadline);
        taskList.add(newTask);
        MessageHandler.sendAddTaskMessage(newTask, getCount());
    }

    /**
     * Adds an Event task to the list
     *
     * @param task  The description of the event
     * @param start The start time of the event
     * @param end   The end time of the event
     */
    public void addEventToTaskList(String task, String start, String end) {
        Event newTask = new Event(task, start, end);
        taskList.add(newTask);
        MessageHandler.sendAddTaskMessage(newTask, getCount());
    }

    /**
     * Deletes a task from the list at a specific task number
     *
     * @param taskNumber The number of the task to delete
     */
    public void deleteFromTaskList(int taskNumber) {
        Task deletedTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        MessageHandler.sendDeleteTaskMessage(deletedTask, getCount());
    }

    /**
     * Marks a task as done
     *
     * @param taskNumber The number of the task to mark
     */
    public void mark(int taskNumber) {
        taskList.get(taskNumber - 1).setDone(true);
        MessageHandler.sendMarkMessage(taskList.get(taskNumber - 1));
    }

    /**
     * Unmarks a task as not done
     *
     * @param taskNumber The number of the task to unmark
     */
    public void unmark(int taskNumber) {
        taskList.get(taskNumber - 1).setDone(false);
        MessageHandler.sendUnmarkMessage(taskList.get(taskNumber - 1));
    }

    /**
     * Finds tasks that contain a specific text string and prints them
     *
     * @param text The text to search for within task name
     */
    public void find(String text) {
        ArrayList<Task> findList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getName().toLowerCase().contains(text.toLowerCase())) {
                findList.add(task);
            }
        }
        if (!findList.isEmpty()) {
            MessageHandler.sendFindList(findList);
        } else {
            MessageHandler.sendMessage("No such task!");
        }
    }
}
