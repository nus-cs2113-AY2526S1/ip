package paddington.task;

import paddington.ui.PaddingtonException;
import paddington.ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks in the Paddington application.
 * The {@link TaskList} class is responsible for managing the collection of tasks, including
 * adding, deleting, marking, and unmarking tasks, as well as listing and searching tasks.
 */
public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns the list of all tasks.
     *
     * @return the list of tasks
     */
    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return the task count
     */
    public static int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task
     * @return the task at the specified index
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the list without printing a message.
     * Used at initialisation load tasks from storage.
     *
     * @param task the task to add
     */
    public static void addTaskSilently(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a task to the list and prints a confirmation message.
     *
     * @param task the task to add
     */
    private static void addTask(Task task) {
        tasks.add(task);
        Ui.printAddTask(task.toString(), getTaskCount());
    }

    /**
     * Deletes a task from the list based on the given index.
     *
     * @param input the index of the task to delete
     */
    public static void deleteTask(String input) {
        int taskIndex = Integer.parseInt(input) - 1;
        String taskString = getTask(taskIndex).toString();
        tasks.remove(taskIndex);
        Ui.printDeleteTask(taskString, getTaskCount());
    }

    /**
     * Lists all the tasks in the list.
     */
    public static void listAllTasks() {
        // Display all saved tasks
        if (tasks.isEmpty()) {
            System.out.println("No saved tasks.");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print("  " + (i + 1) + ".");
            System.out.println(getTask(i).toString());
        }
    }

    /**
     * Searches for tasks containing the given input in their description.
     *
     * @param input the string to search for in task descriptions
     */
    public static void findTask(String input) {
        ArrayList<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(input.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
            return;
        }

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.print("  " + (i + 1) + ".");
            System.out.println(matchingTasks.get(i).toString());
        }
    }

    /**
     * Marks a task as done based on the given index.
     *
     * @param input the index of the task to mark as done
     */
    public static void markTask(String input) {
        int taskIndex = Integer.parseInt(input) - 1;
        Task task = getTask(taskIndex);
        task.markAsDone();
        Ui.printMarkTask(task.toString());
    }

    /**
     * Unmarks a task as done based on the given index.
     *
     * @param input the index of the task to unmark
     */
    public static void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input) - 1;
        Task task = getTask(taskIndex);
        task.unmarkAsDone();
        Ui.printUnmarkTask(task.toString());
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param input the description of the Todo task
     * @throws PaddingtonException if the input is empty
     */
    public static void addTodo(String input) throws PaddingtonException {
        if (input.isEmpty()) {
            throw PaddingtonException.invalidTodo();
        }

        Todo todo = new Todo(input);
        addTask(todo);
    }

    /**
     * Adds an Event task to the list.
     *
     * @param input the description of the Event task with the start and end times
     * @throws PaddingtonException if the input is invalid or missing required parts
     */
    public static void addEvent(String input) throws PaddingtonException {
        if (input.isEmpty()) {
            throw PaddingtonException.invalidEvent();
        }

        String[] processedInput = input.split(" /from ", 2);
        if (processedInput.length != 2) {
            throw new PaddingtonException("Event task should include start and end time!");
        }

        String[] timings = processedInput[1].split(" /to ", 2);
        if (timings.length != 2) {
            throw new PaddingtonException("Event task should include end time!");
        }

        String description = processedInput[0];
        String from = timings[0];
        String to = timings[1];

        Event event = new Event(description, from, to);
        addTask(event);
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param input the description of the Deadline task with the due date
     * @throws PaddingtonException if the input is invalid or missing required parts
     */
    public static void addDeadline(String input) throws PaddingtonException {
        if (input.isEmpty()) {
            throw PaddingtonException.invalidDeadline();
        }

        String[] processedInput = input.split(" /by ", 2);
        if (processedInput.length != 2) {
            throw new PaddingtonException("Deadline task should include due date!");
        }

        String description = processedInput[0];
        String by = processedInput[1];

        Deadline deadline = new Deadline(description, by);
        addTask(deadline);
    }
}
